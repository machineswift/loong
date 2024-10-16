package com.machine.starter.security.handler;

import cn.hutool.json.JSONUtil;
import com.machine.common.model.LoongAppResult;
import com.machine.starter.security.LoongUserDetails;
import com.machine.starter.security.domain.LoginResponse;
import com.machine.starter.security.util.LoongJwtUtil;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private LoongJwtUtil jwtUtils;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {
        LoongUserDetails userDetails = (LoongUserDetails)authentication.getPrincipal();

        httpServletResponse.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();

        // 生成JWT accessToken
        String accessToken = jwtUtils.generateToken(authentication.getName(),"userId",userDetails.getUserId());

        LoongAppResult<String> result = LoongAppResult.success(JSONUtil.toJsonStr(new LoginResponse(accessToken)));
        outputStream.write(JSONUtil.toJsonStr(result).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}