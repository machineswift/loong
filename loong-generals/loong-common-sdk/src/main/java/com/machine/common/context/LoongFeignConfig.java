package com.machine.common.context;

import com.machine.common.envm.system.SystemEnvironmentEnum;
import feign.Logger;
import feign.Request;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.InvalidParameterException;
import java.util.concurrent.TimeUnit;


@Configuration
public class LoongFeignConfig {

    @Value("${spring.profiles.active}")
    private SystemEnvironmentEnum environmentEnum;

    @Bean
    public Logger.Level feignLoggerLevel() {
        return switch (environmentEnum) {
            case DEV, SIT -> Logger.Level.FULL;
            case UAT -> Logger.Level.HEADERS;
            case PET, SIM, PROD -> Logger.Level.BASIC;
            default -> throw new InvalidParameterException("未选择环境");
        };
    }

    @Bean
    public Request.Options options() {
        return new Request.Options(5L, TimeUnit.SECONDS,
                30L, TimeUnit.SECONDS, true);
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new LoongFeignRequestInterceptor();
    }

}
