package com.machine.app.iam.config;

import com.machine.common.context.LoongAuthContext;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class LoongSecurityFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null != authentication) {
            if (authentication.getPrincipal() instanceof LoongUserDetails userDetails) {
                String userId = userDetails.getUserId();
                LoongAuthContext.getContext().setUserId(userId);
            }
        }
        chain.doFilter(request, response);
    }
}
