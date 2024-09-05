package com.machine.app.iam.auth.controller;

import cn.hutool.core.codec.Base64;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.google.code.kaptcha.Producer;
import com.machine.app.iam.auth.controller.vo.response.LoongAuthCaptchaResponseVo;
import com.machine.starter.security.LoongCaptchaConfig;
import io.lettuce.core.api.sync.RedisCommands;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
@RefreshScope
@Tag(name = "认证模块")
@RequestMapping("auth")
public class LoongAuthController {

    @Autowired
    private Producer producer;

    @Autowired
    private RedisCommands<String, String> redisCommands;

    @Operation(summary = "获取验证码")
    @ApiOperationSupport(order = 10)
    @GetMapping("captcha")
    public LoongAuthCaptchaResponseVo getVerifyCode() throws IOException {

        String captchaCode = producer.createText();
        BufferedImage image = producer.createImage(captchaCode);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);
        String base64Img = "data:image/jpeg;base64," + Base64.encode(outputStream.toByteArray());

        String userKey = LoongCaptchaConfig.CAPTCHA_KEY + "_" + UUID.randomUUID().toString().replace("-", "");
        redisCommands.set(userKey, captchaCode);
        redisCommands.expire(userKey, LoongCaptchaConfig.CAPTCHA_EXPIRATION_TIME);
        return new LoongAuthCaptchaResponseVo(userKey, base64Img);
    }

}
