package com.machine.starter.minio;

import io.minio.MinioClient;
import jakarta.annotation.Resource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({LoongMinioProperties.class})
public class LoongMinioAutoConfiguration {

    @Resource
    private LoongMinioProperties minioProperties;

    @Bean
    @ConditionalOnProperty(prefix = "loong.minio",name="endpoint",matchIfMissing = false)
    public MinioClient minioClient() {
        MinioClient minioClient = MinioClient.builder()
                .endpoint(minioProperties.getUrl())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();
        return minioClient;
    }

}

