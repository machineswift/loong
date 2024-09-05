package com.machine.starter.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.StaticCredentialsProvider;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class LoongRedisAutoConfiguration {

    @Autowired
    private LoongRedisProperties redisProperties;

    @Bean
    public StatefulRedisConnection<String, String> connection() {

        RedisClient client = RedisClient.create(RedisURI.builder()
                .withHost(redisProperties.getHost())
                .withPort(redisProperties.getPort()).withAuthentication(
                        new StaticCredentialsProvider("", redisProperties.getPassword().toCharArray()))
                .build());
        return client.connect();
    }

    @Bean
    public RedisCommands<String, String> commands(StatefulRedisConnection<String, String> connection) {
        return connection.sync();
    }

}

