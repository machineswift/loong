package com.machine.test.mq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.function.Consumer;
import java.util.function.Function;

@Slf4j
@Configuration
public class MsgFunction {

    @Bean
    public Function<Message<String>, Message<String>> ss007Function() {
        return msg -> {
            String payload = msg.getPayload();
            return MessageBuilder.withPayload("王二狗第%d说：" + payload)
                    .build();
        };
    }


    @Bean
    public Consumer<String> ss007AutoConsumer() {
        return new Consumer<String>() {
            @Override
            public void accept(String msgData) {
                log.info("ss007AutoConsumer接到消息：{}", msgData.toString());
            }
        };
    }

}
