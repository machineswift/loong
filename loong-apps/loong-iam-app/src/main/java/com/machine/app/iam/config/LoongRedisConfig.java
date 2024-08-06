package com.machine.app.iam.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession
public class LoongRedisConfig {

    @Bean("springSessionDefaultRedisSerializer")
    public RedisSerializer redisSerializer() {
        ObjectMapper om = new ObjectMapper();
        // 处理 java.time 包下的类型
        om.registerModule(new JavaTimeModule());
        return new Jackson2JsonRedisSerializer<>(om, Object.class);
    }


    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        // 设置 key 的序列化方式
        template.setKeySerializer(new StringRedisSerializer());

        ObjectMapper om = new ObjectMapper();
        // 处理 java.time 包下的类型
        om.registerModule(new JavaTimeModule());

        // 设置 value 的序列化方式
        Jackson2JsonRedisSerializer<Object> jacksonSerializer = new Jackson2JsonRedisSerializer<>(om, Object.class);
        template.setValueSerializer(jacksonSerializer);

        // 设置 Hash 的 key 和 value 的序列化方式
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(jacksonSerializer);

        template.afterPropertiesSet();
        return template;
    }
}
