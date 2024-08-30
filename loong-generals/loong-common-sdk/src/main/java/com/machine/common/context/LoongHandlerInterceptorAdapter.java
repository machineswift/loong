package com.machine.common.context;

import com.machine.common.constant.LoongContextConstant;
import org.springframework.lang.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LoongHandlerInterceptorAdapter implements HandlerInterceptor {

    /**
     * 在请求处理之前执行的逻辑
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        String userId = request.getHeader(LoongContextConstant.HEAD_USER_ID);
        if (null == userId || userId.trim().isEmpty()) {
            String feignMethod = request.getRequestURI();
            if (IGNORE_SET.contains(feignMethod)) {
                return true;
            }
            throw new InvalidParameterException("用户Id丢失");
        }
        LoongAppContext.getContext().setUserId(userId);
        return true;
    }

    /**
     * 在请求处理之后、视图渲染之前执行的逻辑
     */
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
    }

    /**
     * 在请求处理完成后执行的逻辑，无论是否发生异常
     */
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                @Nullable Exception ex) throws Exception {
        LoongAppContext.getContext().clear();
    }


    private static final Set<String> IGNORE_SET = new HashSet<>(
            List.of("/loong-iam-service/serve/auth/getBySeries",
                    "/loong-iam-service/server/user/auth_detail",
                    "/loong-iam-service/server/user/getByUserName"));

}
