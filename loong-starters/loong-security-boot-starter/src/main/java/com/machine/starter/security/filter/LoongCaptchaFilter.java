package com.machine.starter.security.filter;

import cn.hutool.core.io.IoUtil;
import cn.hutool.json.JSONUtil;
import com.machine.common.context.LoongAppContext;
import com.machine.starter.security.LoongUserDetails;
import com.machine.starter.security.LoongUserDetailsService;
import com.machine.starter.security.domain.LoginCredentialsRequest;
import com.machine.starter.security.exception.CaptchaAuthException;
import com.machine.starter.security.handler.LoginFailureHandler;
import io.lettuce.core.api.sync.RedisCommands;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.nio.charset.Charset;

public class LoongCaptchaFilter extends UsernamePasswordAuthenticationFilter {

    private final LoginFailureHandler loginFailureHandler;

    private final RedisCommands<String, String> redisCommands;

    private final LoongUserDetailsService userDetailsService;

    public LoongCaptchaFilter(LoginFailureHandler loginFailureHandler,
                              RedisCommands<String, String> redisCommands,
                              LoongUserDetailsService userDetailsService) {
        this.loginFailureHandler = loginFailureHandler;
        this.redisCommands = redisCommands;
        this.userDetailsService = userDetailsService;
    }

    @Override
    @SneakyThrows
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")
                || !isJsonContentType(request)) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        LoginCredentialsRequest credentials = JSONUtil.toBean(IoUtil.read(request.getInputStream(),
                Charset.defaultCharset()), LoginCredentialsRequest.class);

        // 校验验证码
        try {
            validate(credentials.getCaptcha(), credentials.getUserKey());
        } catch (CaptchaAuthException e) {
            // 交给认证失败处理器
            loginFailureHandler.onAuthenticationFailure(request, response, e);
        }

        LoongUserDetails userDetails = (LoongUserDetails) userDetailsService.loadUserByUsername(credentials.getUserName());
        if (null != userDetails) {
            LoongAppContext.getContext().setUserId(userDetails.getUserId());
        }

        //构建登录令牌
        UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken.unauthenticated(
                credentials.getUserName(), credentials.getPassword());

        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private boolean isJsonContentType(HttpServletRequest request) {
        String contentType = request.getContentType();
        if (contentType == null) {
            return false;
        }
        return contentType.equals(MediaType.APPLICATION_JSON_VALUE);
    }

    /**
     * 校验验证码逻辑
     */
    private void validate(String captcha,
                          String userKey) {
        if (StringUtils.isBlank(captcha) || StringUtils.isBlank(userKey)) {
            redisCommands.del(userKey);
            throw new CaptchaAuthException("验证码错误");
        }
        if (!captcha.equals(redisCommands.get(userKey))) {
            redisCommands.del(userKey);
            throw new CaptchaAuthException("验证码错误");
        }
        redisCommands.del(userKey);
    }
}