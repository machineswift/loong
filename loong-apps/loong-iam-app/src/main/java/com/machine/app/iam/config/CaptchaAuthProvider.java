package com.machine.app.iam.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.Objects;

public class CaptchaAuthProvider extends DaoAuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        HttpServletRequest req = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder
                .getRequestAttributes())).getRequest();

        HttpSession session = req.getSession();

        LocalDateTime expirationTime = (LocalDateTime) session.getAttribute("captchaExpirationTime");
        if (null == expirationTime || LocalDateTime.now().isAfter(expirationTime)) {
            session.removeAttribute("captcha");
            session.removeAttribute("captchaExpirationTime");
            throw new AuthenticationServiceException("验证码输入错误");
        }

        String captcha = (String) req.getAttribute("captcha");
        String sessionCaptcha = (String) session.getAttribute("captcha");
        if (captcha == null || !captcha.equalsIgnoreCase(sessionCaptcha)) {
            session.removeAttribute("captcha");
            session.removeAttribute("captchaExpirationTime");
            throw new AuthenticationServiceException("验证码输入错误");
        }

        session.removeAttribute("captcha");
        session.removeAttribute("captchaExpirationTime");
        return super.authenticate(authentication);
    }
}