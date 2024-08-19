package com.machine.common.context;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoongFeignConfig {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return new LoongFeignRequestInterceptor();
    }
}
