package com.machine.app.iam.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}