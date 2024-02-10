package com.machine.starter.minio;

import com.machine.common.tool.yaml.YamlPropertySourceFactory;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource(value = "classpath:loong-minio.yml", factory = YamlPropertySourceFactory.class)
@ConfigurationProperties(prefix = "loong.minio")
public class LoongMinioProperties {

    private String url;

    private String accessKey;

    private String secretKey;
}