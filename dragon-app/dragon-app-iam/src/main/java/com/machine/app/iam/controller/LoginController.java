package com.machine.app.iam.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
@Tag(name = "登录")
public class LoginController {


    @GetMapping("/login")
    @Operation(summary = "登录")
    public ResponseEntity<String> login() {
        return ResponseEntity.ok("尚未登录，请登录");
    }

    @GetMapping("/hello")
    @Operation(summary = "hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello");
    }

    @GetMapping("/helloPost")
    @Operation(summary = "helloPost")
    public ResponseEntity<String> helloPost() {
        return ResponseEntity.ok("helloPost");
    }


}