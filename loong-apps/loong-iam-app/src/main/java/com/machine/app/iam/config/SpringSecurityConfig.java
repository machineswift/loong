package com.machine.app.iam.config;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;


import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Autowired
    private LoongTokenRepository tokenRepository;

    @Autowired
    private LoongUserDetailsService userDetailsService;

//    public SpringSessionBackedSessionRegistry sessionRegistry(FindByIndexNameSessionRepository sessionRepository){
//        SpringSessionBackedSessionRegistry sessionRegistry=new SpringSessionBackedSessionRegistry(sessionRepository);
//        sessionRegistry.
//        return sessionRegistry;
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement((session) -> {
                    session
                            .maximumSessions(1)
                            .maxSessionsPreventsLogin(false)
                            //.sessionRegistry()
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
                })
                .rememberMe((remember) -> remember
                        .rememberMeServices(loongRememberMeServices())
                )
                .authorizeHttpRequests(authorize ->
                        authorize.requestMatchers("/auth/vc", "/auth/login").permitAll()
                                //必须通过用户名密码方式认证
                                .requestMatchers("/admin").fullyAuthenticated()
                                //必须通过RememberMe方式认证
                                .requestMatchers("/rememberme").rememberMe()
                                //.requestMatchers("/admin/**").hasRole("ADMIN")
                                .anyRequest()
                                .authenticated()
                )
                .logout(logout ->
                        logout.logoutUrl("/auth/logout")
                                .logoutSuccessHandler((req, resp, authentication) -> {
                                    resp.setContentType("application/json;charset=utf-8");
                                    PrintWriter out = resp.getWriter();
                                    out.write("注销成功");
                                    out.flush();
                                    out.close();
                                })
                )
                .exceptionHandling(exceptionHandle ->
                        exceptionHandle
                                .accessDeniedHandler((req, resp, e) -> {
                                    resp.setContentType("application/json;charset=utf-8");
                                    PrintWriter out = resp.getWriter();
                                    out.write(e.getMessage());
                                    out.flush();
                                    out.close();
                                })
                                .authenticationEntryPoint((req, resp, e) -> {
                                    resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                                    resp.setContentType("application/json;charset=utf-8");
                                    PrintWriter out = resp.getWriter();
                                    out.write("没有登录");
                                    out.flush();
                                    out.close();
                                })
                )
                .csrf((csrf) -> csrf
                        .csrfTokenRepository(new HttpSessionCsrfTokenRepository()).disable()
                )
                .formLogin(formLogin ->
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
                )
                .authenticationManager(authenticationManager())
        ;

        return http.build();
    }

    @Bean
    public LoongRememberMeServices loongRememberMeServices() {
        LoongRememberMeServices services = new LoongRememberMeServices(
                "loong-remember-service-key", userDetailsService, tokenRepository);
        services.setTokenValiditySeconds(7 * 24 * 60 * 60);
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
        encoders.put("pbkdf2", Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_5());
        encoders.put("scrypt", SCryptPasswordEncoder.defaultsForSpringSecurity_v4_1());
        encoders.put("SHA-1", new org.springframework.security.crypto.password.MessageDigestPasswordEncoder("SHA-1"));
        encoders.put("SHA-256", new org.springframework.security.crypto.password.MessageDigestPasswordEncoder("SHA-256"));
        encoders.put("sha256", new org.springframework.security.crypto.password.StandardPasswordEncoder());
        encoders.put("argon2", Argon2PasswordEncoder.defaultsForSpringSecurity_v5_2());
        encoders.put("ldap", new org.springframework.security.crypto.password.LdapShaPasswordEncoder());
        encoders.put("MD4", new org.springframework.security.crypto.password.Md4PasswordEncoder());
        encoders.put("MD5", new org.springframework.security.crypto.password.MessageDigestPasswordEncoder("MD5"));
        encoders.put("noop", org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance());

        return new DelegatingPasswordEncoder(encodingId, encoders);
    }
}