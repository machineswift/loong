package com.machine.app.iam.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.annotation.Persistent;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RefreshScope
@RequestMapping("hello")
public class HelloController {

    @RequestMapping("resource")
    public Map<String,Object> home() {
        Map<String,Object> model = new HashMap<String,Object>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Hello World");
        return model;
    }

    @GetMapping("hello")
    public String hello() {
        return "hello";
    }

    @PreAuthorize("hasRole('ADMIN') && authentication.name=='admin'")
    @GetMapping("admin")
    public String admin() {
        return "admin";
    }

    @PreAuthorize("authentication.name=='user'")
    @GetMapping("user")
    public String user() {
        return "user";
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