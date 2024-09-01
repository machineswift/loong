package com.machine.starter.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.function.Consumer;
import java.util.function.Function;

@Slf4j
@Configuration
public class LoongLogBinding {

    @Bean
    public Function<Message<String>, Message<String>> loongLogFunction() {
        return msg -> {
            String payload = msg.getPayload();
            log.info("function 接到消息: {}", payload);
            return MessageBuilder
                    .withPayload("function-->" + payload)
                    .build();
        };
    }


    @Bean
    public Consumer<Message<String>> loongLogConsumer() {
        return msgData -> log.info("consumer 接到消息:{}", msgData.getPayload());
    }

}
