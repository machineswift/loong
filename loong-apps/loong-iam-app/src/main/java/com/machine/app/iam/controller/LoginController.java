package com.machine.app.iam.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("login")
public class LoginController {


    @GetMapping("/login")
    public ResponseEntity<String> login() {
        return ResponseEntity.ok("尚未登录，请登录");
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello");
    }

    @GetMapping("/helloPost")
    public ResponseEntity<String> helloPost() {
        return ResponseEntity.ok("helloPost");
    }

    @GetMapping("/getUser")
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