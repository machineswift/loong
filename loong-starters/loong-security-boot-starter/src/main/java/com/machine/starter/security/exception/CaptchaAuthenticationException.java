package com.machine.starter.security.exception;

import org.springframework.security.core.AuthenticationException;

public class CaptchaAuthenticationException extends AuthenticationException {
    public CaptchaAuthenticationException(String msg) {
        super(msg);
    }
}