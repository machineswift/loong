package com.machine.common.context;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoongWebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoongHandlerInterceptorAdapter())
                .excludePathPatterns("/loong-iam-service/serve/auth/getBySeries",
                        "/loong-iam-service/server/user/auth_detail",
                        "/loong-iam-service/server/user/getByUserName");
    }
}
