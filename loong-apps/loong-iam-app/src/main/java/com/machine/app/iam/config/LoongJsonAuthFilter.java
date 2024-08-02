package com.machine.app.iam.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.machine.app.iam.auth.controller.vo.request.LoginCredentialsRequestVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;


public class LoongJsonAuthFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST") || !isJsonContentType(request)) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        try {
            LoginCredentialsRequestVo credentials = new ObjectMapper().readValue(request.getInputStream(), LoginCredentialsRequestVo.class);
            request.setAttribute("captcha", credentials.getCaptcha());

            //构建登录令牌
            UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken.unauthenticated(
                    credentials.getUsername(), credentials.getPassword());

            // Allow subclasses to set the "details" property
            setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isJsonContentType(HttpServletRequest request) {
        String contentType = request.getContentType();
        if (contentType == null) {
            return false;
        }

        return contentType.equals(MediaType.APPLICATION_JSON_VALUE);
    }
}