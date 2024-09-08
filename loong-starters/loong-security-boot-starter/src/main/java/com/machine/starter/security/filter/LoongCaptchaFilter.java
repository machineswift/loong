package com.machine.starter.security.filter;

import com.machine.starter.security.exception.CaptchaAuthException;
import com.machine.starter.security.handler.LoginFailureHandler;
import io.lettuce.core.api.sync.RedisCommands;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.machine.starter.security.LoongSecurityConstant.*;

@Component
public class LoongCaptchaFilter extends OncePerRequestFilter {

    @Autowired
    private LoginFailureHandler loginFailureHandler;

    @Autowired
    private RedisCommands<String, String> redisCommands;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        String url = httpServletRequest.getRequestURI();
        if ((httpServletRequest.getContextPath() + LOGIN_URL).equals(url) && httpServletRequest.getMethod().equals("POST")) {
            // 校验验证码
            try {
                validate(httpServletRequest);
            } catch (CaptchaAuthException e) {
                // 交给认证失败处理器
                loginFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    // 校验验证码逻辑
    private void validate(HttpServletRequest httpServletRequest) {
        String code = httpServletRequest.getParameter("captcha");
        String userKey = httpServletRequest.getParameter("userKey");
        if (StringUtils.isBlank(code) || StringUtils.isBlank(userKey)) {
            redisCommands.del(userKey);
            throw new CaptchaAuthException("验证码错误");
        }
        if (!code.equals(redisCommands.get(userKey))) {
            redisCommands.del(userKey);
            throw new CaptchaAuthException("验证码错误");
        }
        redisCommands.del(userKey);
    }
}