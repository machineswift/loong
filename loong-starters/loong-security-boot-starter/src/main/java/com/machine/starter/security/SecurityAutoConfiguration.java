package com.machine.starter.security;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({LoongSecurityProperties.class})
public class SecurityAutoConfiguration {


}