package com.machine.starter.common.config;

import com.machine.common.exception.BusinessException;
import com.machine.common.model.LoongAppResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class LoongExceptionHandler {

    /**
     * 认证异常
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = AuthenticationException.class)
    private LoongAppResult<Objects> errorHandler(AuthenticationException exception) {
        log.error(exception.getMessage(), exception);
        return LoongAppResult.fail(HttpStatus.UNAUTHORIZED.value(), "", exception.getMessage());
    }


    /**
     * 权限异常
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = AccessDeniedException.class)
    private LoongAppResult<Objects> errorHandler(AccessDeniedException exception) {
        log.error(exception.getMessage(), exception);
        return LoongAppResult.fail(HttpStatus.UNAUTHORIZED.value(), "", exception.getMessage());
    }


    /**
     * 业务异常处理
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = BusinessException.class)
    private LoongAppResult<Objects> errorHandler(BusinessException exception) {
        log.error(exception.getMessage(), exception);
        return LoongAppResult.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getCode(), exception.getMessage());
    }

    /**
     * 500异常处理
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public LoongAppResult<Objects> errorHandler(Exception exception) {
        log.error(exception.getMessage(), exception);
        return LoongAppResult.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "", exception.getMessage());
    }
}
