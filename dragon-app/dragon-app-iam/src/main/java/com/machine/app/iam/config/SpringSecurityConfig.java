package com.machine.app.iam.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.machine.app.iam.authentication.CustomAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.io.PrintWriter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/public/**").permitAll().anyRequest().authenticated()).formLogin(formLogin -> formLogin
                //.loginPage("/login")
                //.loginProcessingUrl("/login")
                .successHandler((req, resp, authentication) -> {
                    Object principal = authentication.getPrincipal();
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.write(new ObjectMapper().writeValueAsString(principal));
                    out.flush();
                    out.close();
                }).failureHandler((req, resp, e) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.write(e.getMessage());
                    out.flush();
                    out.close();
                }).permitAll()).exceptionHandling(exceptionHandle ->
                exceptionHandle.authenticationEntryPoint(new CustomAuthenticationEntryPoint())).rememberMe(Customizer.withDefaults());

        return http.build();
    }

}