package com.machine.common.context;

import com.machine.common.constant.LoongContextConstant;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.SneakyThrows;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.lang.reflect.Method;
import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LoongFeignRequestInterceptor implements RequestInterceptor {

    @Override
    @SneakyThrows
    public void apply(RequestTemplate template) {
        String userId = LoongAppContext.getContext().getUserId();
        if (null == userId || userId.trim().isEmpty()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (null != authentication) {
                Object object = authentication.getPrincipal();
                Method method = authentication.getPrincipal().getClass().getMethod("getUserId");
                userId = method.invoke(object).toString();
                template.header(LoongContextConstant.HEAD_USER_ID, userId);
                return;
            }
            String feignMethod = template.feignTarget().name() + template.path();
            if (IGNORE_SET.contains(feignMethod)) {
                return;
            }
            throw new InvalidParameterException("用户Id丢失");
        }
        template.header(LoongContextConstant.HEAD_USER_ID, userId);
    }

    private static final Set<String> IGNORE_SET = new HashSet<>(
            List.of("loong-iam-service/loong-iam-service/serve/auth/getBySeries",
                    "loong-iam-service/loong-iam-service/server/user/auth_detail",
                    "loong-iam-service/loong-iam-service/server/user/getByUserName"));
}
