package com.machine.starter.security.filter;

import cn.hutool.core.util.StrUtil;
import com.machine.common.context.LoongAppContext;
import com.machine.starter.security.exception.JwtAuthenticationException;
import com.machine.starter.security.util.LoongJwtUtil;
import com.machine.starter.security.LoongUserDetailsService;
import io.jsonwebtoken.Claims;
import io.lettuce.core.api.sync.RedisCommands;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.machine.common.constant.LoongRedisPrefixConstant.Iam.IAM_AUTH_JWT;
import static com.machine.starter.security.LoongSecurityConstant.AUTH_HEADER;
import static com.machine.starter.security.LoongSecurityConstant.BEARER_TYPE;

@Component
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    @Autowired
    private LoongJwtUtil jwtUtil;

    @Autowired
    private LoongUserDetailsService userDetailService;

    @Autowired
    private RedisCommands<String, String> redisCommands;


    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String jwt = request.getHeader(AUTH_HEADER);
        // 这里如果没有jwt，继续往后走，因为后面还有鉴权管理器等去判断是否拥有身份凭证，所以是可以放行的
        // 没有jwt相当于匿名访问，若有一些接口是需要权限的，则不能访问这些接口
        if (StrUtil.isBlankOrUndefined(jwt)) {
            chain.doFilter(request, response);
            return;
        }

        Claims claim = jwtUtil.getClaimsByToken(jwt.substring(BEARER_TYPE.length()));
        LoongAppContext.getContext().setUserId(claim.get("userId", String.class));
        String username = claim.getSubject();

        //验证是否为黑名单
        if (null != redisCommands.get(IAM_AUTH_JWT + claim.getId())) {
            throw new JwtAuthenticationException("该令牌失效，请重新获取令牌");
        }

        // 获取用户的权限等信息
        UserDetails userDetails = userDetailService.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(token);
        chain.doFilter(request, response);
    }
}