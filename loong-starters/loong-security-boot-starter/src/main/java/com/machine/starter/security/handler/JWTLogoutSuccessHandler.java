package com.machine.starter.security.handler;

import cn.hutool.json.JSONUtil;
import com.machine.common.model.LoongAppResult;
import com.machine.starter.security.util.LoongJwtUtil;
import io.jsonwebtoken.Claims;
import io.lettuce.core.api.sync.RedisCommands;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.machine.common.constant.LoongRedisPrefixConstant.Iam.IAM_AUTH_JWT;
import static com.machine.starter.security.LoongSecurityConstant.AUTH_HEADER;
import static com.machine.starter.security.LoongSecurityConstant.BEARER_TYPE;

@Component
public class JWTLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private LoongJwtUtil jwtUtil;

    @Autowired
    private RedisCommands<String, String> redisCommands;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Authentication authentication) throws IOException {
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(httpServletRequest, httpServletResponse, authentication);
        }

        /*
            注销登录时，缓存JWT至Redis，且缓存有效时间设置为JWT的有效期。
            请求资源时判断是否存在缓存的黑名单中，存在则拒绝访问。
         */
        String jwt = httpServletRequest.getHeader(AUTH_HEADER);
        Claims claim = jwtUtil.getClaimsByToken(jwt.substring(BEARER_TYPE.length()));
        redisCommands.set(IAM_AUTH_JWT + claim.getId(), claim.getSubject());
        redisCommands.expire(IAM_AUTH_JWT + claim.getId(),
                (claim.getExpiration().getTime() - System.currentTimeMillis()) / 1000);

        //设置头为空字符串，依赖前端清除浏览器缓存的 token
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        httpServletResponse.setHeader(AUTH_HEADER, "");
        LoongAppResult<String> result = LoongAppResult.success("注销成功");
        outputStream.write(JSONUtil.toJsonStr(result).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
