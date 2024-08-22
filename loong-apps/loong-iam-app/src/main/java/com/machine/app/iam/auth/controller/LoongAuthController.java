package com.machine.app.iam.auth.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.google.code.kaptcha.Producer;
import com.machine.app.iam.config.LoongCaptchaConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Slf4j
@RestController
@RefreshScope
@Tag(name = "认证模块")
@RequestMapping("auth")
public class LoongAuthController {

    @Autowired
    private Producer producer;

    @Operation(summary = "获取验证码")
    @ApiOperationSupport(order = 10)
    @GetMapping("captcha")
    public void getVerifyCode(HttpServletResponse resp,
                              HttpSession session) throws IOException {
        resp.setContentType("image/jpeg");
        String text = producer.createText();
        session.setAttribute("captcha", text);
        session.setAttribute("captchaExpirationTime",
                System.currentTimeMillis() + LoongCaptchaConfig.CAPTCHA_EXPIRATION_TIME * 1000);
        BufferedImage image = producer.createImage(text);
        try (ServletOutputStream out = resp.getOutputStream()) {
            ImageIO.write(image, "jpg", out);
        }
    }

}
