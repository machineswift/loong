package com.machine.starter.mq;


import com.machine.common.tool.yaml.YamlPropertySourceFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration(proxyBeanMethods = false)
@PropertySource(value = "classpath:loong-mq.yml", factory = YamlPropertySourceFactory.class)
public class LoongMqAutoConfiguration {

}

