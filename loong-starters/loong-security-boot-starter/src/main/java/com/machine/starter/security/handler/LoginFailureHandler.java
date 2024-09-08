package com.machine.starter.security.handler;

import cn.hutool.json.JSONUtil;
import com.machine.common.model.LoongAppResult;
import com.machine.starter.security.exception.CaptchaAuthException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        AuthenticationException e) throws IOException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();

        LoongAppResult<String> result;
        if (e instanceof CaptchaAuthException) {
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            result = LoongAppResult.fail(HttpStatus.UNAUTHORIZED.value(), "iam.auth.authentication", e.getMessage());
        } else {
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            result = LoongAppResult.fail(HttpStatus.UNAUTHORIZED.value(), "iam.auth.authentication", e.getMessage());
        }
        outputStream.write(JSONUtil.toJsonStr(result).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }

}