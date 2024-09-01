package com.machine.app.manage.hello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("hello")
public class HelloController {

    @Autowired
    private StreamBridge streamBridge;

    @GetMapping("send")
    public void send() {
        streamBridge.send("loongLogProducer",
                MessageBuilder.withPayload("hello")
                        .build());
    }

}