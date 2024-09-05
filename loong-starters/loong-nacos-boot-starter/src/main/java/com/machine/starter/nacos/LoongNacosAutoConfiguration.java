package com.machine.starter.nacos;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({LoongNacosProperties.class})
public class LoongNacosAutoConfiguration {


}

