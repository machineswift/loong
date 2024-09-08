package com.machine.starter.security.controller;

import com.machine.common.exception.BusinessException;
import cn.hutool.core.util.ClassUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Constructor;
import java.util.*;

@RestController
public class FilterErrorController extends BasicErrorController {

    public FilterErrorController(ServerProperties serverProperties) {
        super(new DefaultErrorAttributes(), serverProperties.getError());
    }

    @Override
    @SneakyThrows
    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) throws AuthenticationException {
        HttpStatus status = getStatus(request);
        if (status == HttpStatus.NO_CONTENT) {
            return new ResponseEntity<>(status);
        }
        Map<String, Object> body = getErrorAttributes(request, getErrorAttributeOptions(request, MediaType.ALL));

        String message = body.get("message").toString();
        String className = body.get("exception").toString();
        if (containAccessDeniedException(className)) {
            Class<?> clazz = Class.forName(className);
            // 获取带有一个 String 参数的构造函数
            Constructor<?> constructor = clazz.getConstructor(String.class);
            // 创建实例
            throw (AuthenticationException) constructor.newInstance(message);
        }

        if (containAuthenticationException(className)) {
            throw new AccessDeniedException(message);
        }

        throw new BusinessException("", body.get("message").toString());
    }

    private boolean containAccessDeniedException(String className) {
        if (accessDeniedExceptionSet.isEmpty()) {
            synchronized (FilterErrorController.class) {
                if (accessDeniedExceptionSet.isEmpty()) {
                    Class<?> superClass = AuthenticationException.class;
                    Set<Class<?>> subclasses = findSubClassesRecursively("org.springframework.security", superClass);
                    subclasses.addAll(findSubClassesRecursively("com.machine.starter.security.exception", superClass));
                    accessDeniedExceptionSet.addAll(subclasses.stream().map(Class::getName).toList());
                }
            }
        }
        return accessDeniedExceptionSet.contains(className);
    }

    private boolean containAuthenticationException(String className) {
        if (authenticationExceptionSet.isEmpty()) {
            synchronized (FilterErrorController.class) {
                if (authenticationExceptionSet.isEmpty()) {
                    Class<?> superClass = AccessDeniedException.class;
                    Set<Class<?>> subclasses = findSubClassesRecursively("org.springframework.security", superClass);
                    subclasses.addAll(findSubClassesRecursively("com.machine.starter.security.exception", superClass));
                    authenticationExceptionSet.addAll(subclasses.stream().map(Class::getName).toList());
                }
            }
        }
        return authenticationExceptionSet.contains(className);
    }

    private static Set<Class<?>> findSubClassesRecursively(String packageName,
                                                           Class<?> superClass) {
        Set<Class<?>> subclasses = new HashSet<>();
        Set<Class<?>> foundClasses = ClassUtil.scanPackageBySuper(packageName, superClass);
        for (Class<?> clazz : foundClasses) {
            subclasses.add(clazz);
            subclasses.addAll(findSubClassesRecursively(packageName, clazz));
        }
        return subclasses;
    }


    private static final Set<String> accessDeniedExceptionSet = new HashSet<>();
    private static final Set<String> authenticationExceptionSet = new HashSet<>();
}
