package com.machine.test.temp.controller;


import com.machine.test.temp.agent.LoongCookie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RefreshScope
@RequestMapping("hello")
public class HelloController {

    @GetMapping("agent")
    public void getVerifyCode() {
        LoongCookie cookie = new LoongCookie();
        cookie.setPath("/machine/000000000000");
        log.info(cookie.getPath());
    }
}
