package com.machine.starter.redis;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({LoongRedisProperties.class})
public class LoongRedisAutoConfiguration {


}

