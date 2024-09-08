package com.machine.starter.security.exception;

import org.springframework.security.core.AuthenticationException;

public class CaptchaAuthException extends AuthenticationException {
    public CaptchaAuthException(String msg) {
        super(msg);
    }
}