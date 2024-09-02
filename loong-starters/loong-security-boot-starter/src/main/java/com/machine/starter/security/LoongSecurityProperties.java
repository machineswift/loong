package com.machine.starter.security;

import com.machine.common.tool.yaml.YamlPropertySourceFactory;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource(value = "classpath:loong-security.yml", factory = YamlPropertySourceFactory.class)
@ConfigurationProperties(prefix = "loong.security")
public class LoongSecurityProperties {
    private String example;
}