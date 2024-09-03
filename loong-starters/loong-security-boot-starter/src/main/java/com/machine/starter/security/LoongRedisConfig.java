package com.machine.starter.security;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.machine.starter.security.serializer.LoongUserDetailsDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.jackson2.SecurityJackson2Modules;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisIndexedHttpSession;
import org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration;

@Configuration
@EnableRedisIndexedHttpSession(
        redisNamespace = "loong:session",
        maxInactiveIntervalInSeconds = 24 * 3600)
public class LoongRedisConfig {

    @Bean
    public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
        ObjectMapper mapper = new ObjectMapper();

        mapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);

        mapper.registerModule(new JavaTimeModule());
        mapper.registerModules(SecurityJackson2Modules.getModules(getClass().getClassLoader()));

        SimpleModule module = new SimpleModule();
        module.addDeserializer(LoongUserDetails.class, new LoongUserDetailsDeserializer());
        mapper.registerModule(module);

        return new GenericJackson2JsonRedisSerializer(mapper);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisSerializer<Object> serializer = springSessionDefaultRedisSerializer();
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(serializer);
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);
        template.setDefaultSerializer(springSessionDefaultRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public RedisHttpSessionConfiguration redisHttpSessionConfiguration() {
        RedisHttpSessionConfiguration config = new RedisHttpSessionConfiguration();
        config.setDefaultRedisSerializer(springSessionDefaultRedisSerializer());
        return config;
    }
}
