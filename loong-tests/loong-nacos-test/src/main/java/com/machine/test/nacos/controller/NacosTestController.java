package com.machine.test.nacos.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RefreshScope
@RequestMapping("nacos")
public class NacosTestController {


    @Value("${ss:rr}")
    private String ss;


    @GetMapping("test")
    public String queryCustomerDetail() {
        log.info(ss);
        return ss;
    }
}
