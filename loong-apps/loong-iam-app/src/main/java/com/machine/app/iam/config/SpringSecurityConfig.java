package com.machine.app.iam.config;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.session.data.redis.RedisIndexedSessionRepository;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.web.cors.CorsConfiguration;


import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true, securedEnabled = true, proxyTargetClass = true)
public class SpringSecurityConfig {

    @Autowired
    private LoongTokenRepository tokenRepository;

    @Autowired
    private LoongUserDetailsService userDetailsService;

    @Autowired
    private RedisIndexedSessionRepository sessionRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors((cors) -> {
            cors.configurationSource(request -> {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowCredentials(true);
                config.addAllowedOrigin("*");
                config.addAllowedHeader("*");
                config.addAllowedMethod("*");
                config.setMaxAge(3600L);
                return config;
            });
        });
        http.sessionManagement((session) -> {
            session
                    .maximumSessions(1)
                    .maxSessionsPreventsLogin(false)
                    .sessionRegistry(new SpringSessionBackedSessionRegistry<>(sessionRepository))
                    .expiredSessionStrategy(event -> {
                        HttpServletResponse response = event.getResponse();
                        response.setContentType("application/json;charset=utf-8");
                        Map<String, Object> result = new HashMap<>();
                        result.put("status", 500);
                        result.put("msg", "当前会话已经失效，请重新登录");
                        String s = new ObjectMapper().writeValueAsString(result);
                        response.getWriter().print(s);
                        response.flushBuffer();
                    });
        });
        http.rememberMe((remember) ->
                remember
                        .rememberMeServices(loongRememberMeServices())
        );
        http.authorizeHttpRequests(authorize ->
                authorize.requestMatchers(
                                "/auth/vc",
                                "/auth/login",
                                "/hello/resource").permitAll()
                        //必须通过用户名密码方式认证
                        .requestMatchers("/admin").fullyAuthenticated()
                        //必须通过RememberMe方式认证
                        .requestMatchers("/rememberme").rememberMe()
                        //.requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest()
                        .authenticated()
        );
        http.logout(logout ->
                logout.logoutUrl("/auth/logout")
                        .logoutSuccessHandler((req, resp, authentication) -> {
                            resp.setContentType("application/json;charset=utf-8");
                            PrintWriter out = resp.getWriter();
                            out.write("注销成功");
                            out.flush();
                            out.close();
                        })
        );
        http.exceptionHandling(exceptionHandle ->
                exceptionHandle
                        .authenticationEntryPoint((req, resp, e) -> {
                            resp.setContentType("application/json;charset=utf-8");
                            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            PrintWriter out = resp.getWriter();
                            out.write("未授权访问");
                            out.flush();
                            out.close();
                        })
                        .accessDeniedHandler((req, resp, e) -> {
                            resp.setContentType("application/json;charset=utf-8");
                            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
                            PrintWriter out = resp.getWriter();
                            out.write("访问被拒绝");
                            out.flush();
                            out.close();
                        })
        );
        http.csrf((csrf) ->
                csrf
                        .csrfTokenRepository(new HttpSessionCsrfTokenRepository()).disable()
        );
        http.formLogin(formLogin ->
                formLogin.loginProcessingUrl("/auth/login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .successHandler((req, resp, authentication) -> {
                            Object principal = authentication.getPrincipal();
                            JSONObject jsonObj = JSONUtil.parseObj(new ObjectMapper().writeValueAsString(principal));
                            jsonObj.remove("password");

                            resp.setContentType("application/json;charset=utf-8");
                            PrintWriter out = resp.getWriter();
                            out.write(jsonObj.toString());
                            out.flush();
                            out.close();
                        })
                        .failureHandler((req, resp, e) -> {
                            resp.setContentType("application/json;charset=utf-8");
                            PrintWriter out = resp.getWriter();
                            out.write(e.getMessage());
                            out.flush();
                            out.close();
                        }).permitAll()
        );
        http.authenticationManager(authenticationManager());

        return http.build();
    }

    @Bean
    public HttpFirewall firewall() {
        return new StrictHttpFirewall();
    }

    @Bean
    public CookieSerializer httpSessionIdResolver() {
        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
        cookieSerializer.setSameSite("strict");
        return cookieSerializer;
    }

    @Bean
    public LoongRememberMeServices loongRememberMeServices() {
        LoongRememberMeServices services = new LoongRememberMeServices(
                "loong-secret-key", userDetailsService, tokenRepository);
        services.setTokenValiditySeconds(7 * 24 * 60 * 60);
        services.setParameter("rememberMe");
        return services;
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        CaptchaAuthProvider provider = new CaptchaAuthProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setUserDetailsPasswordService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(provider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        String encodingId = "scrypt@SpringSecurity_v5_8";
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        encoders.put("pbkdf2@SpringSecurity_v5_8", Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8());
        encoders.put("scrypt@SpringSecurity_v5_8", SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8());
        encoders.put("argon2@SpringSecurity_v5_8", Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8());

        //不推荐以下加密方式
//        encoders.put("pbkdf2", Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_5());
//        encoders.put("scrypt", SCryptPasswordEncoder.defaultsForSpringSecurity_v4_1());
//        encoders.put("SHA-1", new org.springframework.security.crypto.password.MessageDigestPasswordEncoder("SHA-1"));
//        encoders.put("SHA-256", new org.springframework.security.crypto.password.MessageDigestPasswordEncoder("SHA-256"));
//        encoders.put("sha256", new org.springframework.security.crypto.password.StandardPasswordEncoder());
//        encoders.put("argon2", Argon2PasswordEncoder.defaultsForSpringSecurity_v5_2());
//        encoders.put("ldap", new org.springframework.security.crypto.password.LdapShaPasswordEncoder());
//        encoders.put("MD4", new org.springframework.security.crypto.password.Md4PasswordEncoder());
//        encoders.put("MD5", new org.springframework.security.crypto.password.MessageDigestPasswordEncoder("MD5"));
//        encoders.put("noop", org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance());

        return new DelegatingPasswordEncoder(encodingId, encoders);
    }
}