package com.machine.common.context;

import com.machine.common.constant.LoongContextConstant;
import org.springframework.lang.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoongHandlerInterceptorAdapter implements HandlerInterceptor {

    /**
     * 在请求处理之前执行的逻辑
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        String userId = request.getHeader(LoongContextConstant.HEAD_USER_ID);
        LoongAuthContext.getContext().setUserId(userId);
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
        LoongAuthContext.getContext().clear();
    }
}
