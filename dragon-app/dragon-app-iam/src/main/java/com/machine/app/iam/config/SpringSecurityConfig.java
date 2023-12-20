package com.machine.app.iam.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import java.io.PrintWriter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.
                authorizeHttpRequests(authorize ->
                        authorize.requestMatchers("/public/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated())
                .formLogin(formLogin ->
                        formLogin.loginProcessingUrl("/doLogin")
                                .usernameParameter("username")
                                .passwordParameter("password")
                                .successHandler((req, resp, authentication) -> {
                                    Object principal = authentication.getPrincipal();
                                    resp.setContentType("application/json;charset=utf-8");
                                    PrintWriter out = resp.getWriter();
                                    out.write(new ObjectMapper().writeValueAsString(principal));
                                    out.flush();
                                    out.close();
                                })
                                .failureHandler((req, resp, e) -> {
                                    resp.setContentType("application/json;charset=utf-8");
                                    PrintWriter out = resp.getWriter();
                                    out.write(e.getMessage());
                                    out.flush();
                                    out.close();
                                }).permitAll())

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

}