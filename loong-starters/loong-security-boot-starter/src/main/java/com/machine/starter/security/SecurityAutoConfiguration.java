package com.machine.starter.security;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.StaticCredentialsProvider;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class SecurityAutoConfiguration {

    @Value("${loong.redis.host}")
    private String host;
    @Value("${loong.redis.port}")
    private Integer port;
    @Value("${loong.redis.password}")
    private String password;

    @Bean
    public StatefulRedisConnection<String, String> connection() {

        RedisClient client = RedisClient.create(RedisURI.builder()
                .withHost(host)
                .withPort(port).withAuthentication(new StaticCredentialsProvider("", password.toCharArray()))
                .build());
        return client.connect();
    }

    @Bean
    public RedisCommands<String, String> commands(StatefulRedisConnection<String, String> connection) {
        return connection.sync();
    }

}