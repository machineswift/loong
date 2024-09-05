//package com.machine.starter.redis;
//
//import com.machine.common.tool.yaml.YamlPropertySourceFactory;
//import lombok.Data;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//
//@Data
//@Configuration
//@PropertySource(value = "classpath:loong-redis.yml", factory = YamlPropertySourceFactory.class)
//@ConfigurationProperties(prefix = "loong.redis")
//public class LoongRedisProperties {
//
//    @Value("${loong.redis.host}")
//    private String host;
//
//    @Value("${loong.redis.port}")
//    private Integer port;
//
//    @Value("${loong.redis.password}")
//    private String password;
//}