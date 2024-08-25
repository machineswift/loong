package com.machine.app.iam.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Knife4jIamConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Loong 用户系统API")
                        .version("1.0")
                        .description("Knife4j集成springdoc-openapi示例")
                        .termsOfService("http://www.machine.com")
                        .license(new License().name("MIT")
                                .url("http://www.machine.com")));
    }

}
