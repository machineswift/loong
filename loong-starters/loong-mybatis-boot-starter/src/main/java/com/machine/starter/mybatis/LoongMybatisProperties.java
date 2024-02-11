package com.machine.starter.mybatis;

import com.machine.common.tool.yaml.YamlPropertySourceFactory;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource(value = "classpath:loong-mybatis.yml", factory = YamlPropertySourceFactory.class)
@ConfigurationProperties(prefix = "loong.mybatis")
public class LoongMybatisProperties {
    private String example;
}