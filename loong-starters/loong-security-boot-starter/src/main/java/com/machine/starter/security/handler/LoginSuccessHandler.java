package com.machine.starter.security.handler;

import cn.hutool.json.JSONUtil;
import com.machine.common.model.LoongAppResult;
import com.machine.starter.security.LoongUserDetails;
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

import static com.machine.starter.security.LoongSecurityConstant.BARE_TOKEN;

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
        // 生成JWT，并放置到请求头中
        String jwt = jwtUtils.generateToken(authentication.getName(),"userId",userDetails.getUserId());
        httpServletResponse.setHeader(LoongJwtUtil.HEADER_STRING, BARE_TOKEN + jwt);

        LoongAppResult<String> result = LoongAppResult.success("登录成功");
        outputStream.write(JSONUtil.toJsonStr(result).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}