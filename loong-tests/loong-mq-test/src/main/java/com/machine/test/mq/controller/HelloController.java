package com.machine.test.mq.controller;

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
        streamBridge.send("producerManaual",
                MessageBuilder.withPayload("hello")
                        .build());
    }

    @GetMapping("send2")
    public void send2() {
        streamBridge.send("producerManauala",
                MessageBuilder.withPayload("hello1111")
                        .build());

        streamBridge.send("producerManaualb",
                MessageBuilder.withPayload("hello2222")
                        .build());
    }
}

