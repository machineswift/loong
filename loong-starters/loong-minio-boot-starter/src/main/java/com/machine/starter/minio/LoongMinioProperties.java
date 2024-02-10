package com.machine.starter.minio;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource("classpath:loong-minio.yml")
@ConfigurationProperties(prefix = "loong.minio")
public class LoongMinioProperties {

    private String url;

    private String accessKey;

    private String secretKey;
}