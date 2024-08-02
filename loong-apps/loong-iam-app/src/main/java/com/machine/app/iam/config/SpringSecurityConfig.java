package com.machine.app.iam.config;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import java.io.PrintWriter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Autowired
    private LoongUserDetailsService userDetailsService;

    @Bean
    public AuthenticationProvider kaptchaAuthProvider() {
        CaptchaAuthProvider provider = new CaptchaAuthProvider();
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize ->
                        authorize.requestMatchers("/auth/vc", "/auth/login").permitAll()
                                //.requestMatchers("/admin/**").hasRole("ADMIN")
                                .anyRequest()
                                .authenticated())
                .addFilterAt(new LoongJsonAuthFilter(), UsernamePasswordAuthenticationFilter.class)
//                .formLogin(formLogin ->
//                        formLogin.loginProcessingUrl("/doLogin")
//                                .usernameParameter("username")
//                                .passwordParameter("password")
//                                .successHandler((req, resp, authentication) -> {
//                                    Object principal = authentication.getPrincipal();
//                                    resp.setContentType("application/json;charset=utf-8");
//                                    PrintWriter out = resp.getWriter();
//                                    out.write(new ObjectMapper().writeValueAsString(principal));
//                                    out.flush();
//                                    out.close();
//                                })
//                                .failureHandler((req, resp, e) -> {
//                                    resp.setContentType("application/json;charset=utf-8");
//                                    PrintWriter out = resp.getWriter();
//                                    out.write(e.getMessage());
//                                    out.flush();
//                                    out.close();
//                                }).permitAll())
                .logout(logout ->
                        logout.logoutUrl("/logout")
                                .logoutSuccessHandler((req, resp, authentication) -> {
                                    resp.setContentType("application/json;charset=utf-8");
                                    PrintWriter out = resp.getWriter();
                                    out.write("注销成功");
                                    out.flush();
                                    out.close();
                                }))
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
                .rememberMe(Customizer.withDefaults());

        return http.build();
    }


    @Bean
    public LoongJsonAuthFilter jsonLoginFilter() {
        LoongJsonAuthFilter filter = new LoongJsonAuthFilter();
        filter.setAuthenticationSuccessHandler((req, resp, auth) -> {
            Object principal = auth.getPrincipal();
            JSONObject jsonObj = JSONUtil.parseObj(new ObjectMapper().writeValueAsString(principal));
            jsonObj.remove("password");

            resp.setContentType("application/json;charset=utf-8");
            PrintWriter out = resp.getWriter();
            out.write(jsonObj.toString());
            out.flush();
            out.close();
        });
        filter.setAuthenticationFailureHandler((req, resp, e) -> {
            resp.setContentType("application/json;charset=utf-8");
            PrintWriter out = resp.getWriter();
            out.write(e.getMessage());
            out.flush();
            out.close();
        });
        filter.setAuthenticationManager(authenticationManager());
        filter.setFilterProcessesUrl("/auth/login");
        return filter;
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        CaptchaAuthProvider provider = new CaptchaAuthProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(provider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}