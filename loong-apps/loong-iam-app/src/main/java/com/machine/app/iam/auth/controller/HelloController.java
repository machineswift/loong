package com.machine.app.iam.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@Slf4j
@RestController
@RefreshScope
@RequestMapping("hello")
public class HelloController {

    @GetMapping("hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("rememberme")
    public String rememberMe() {
        return "rememberme";
    }
    @GetMapping("getUser")
    public String getUser() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        System.out.println("name =" + name);
        System.out.println("authorities=" + authorities);

        new Thread(() -> {
            Authentication authentication1 =
                    SecurityContextHolder.getContext().getAuthentication();
            String name1 = authentication1.getName();
            Collection<? extends GrantedAuthority> authorities1 = authentication1.getAuthorities();
            System.out.println("name =" + name1);
            System.out.println("authorities=" + authorities1);
        }).start();
        return name;
    }

}