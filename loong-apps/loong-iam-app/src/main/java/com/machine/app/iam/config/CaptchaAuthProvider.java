package com.machine.app.iam.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

public class CaptchaAuthProvider extends DaoAuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        HttpServletRequest req = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder
                .getRequestAttributes())).getRequest();

        String captcha = (String) req.getAttribute("captcha");
        String sessionCaptcha = (String) req.getSession().getAttribute("captcha");
        if (captcha != null && captcha.equalsIgnoreCase(sessionCaptcha)) {
            return super.authenticate(authentication);
        }
        throw new AuthenticationServiceException("验证码输入错误");
    }
}