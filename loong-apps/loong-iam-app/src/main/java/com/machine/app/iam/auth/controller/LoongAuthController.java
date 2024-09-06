package com.machine.app.iam.auth.controller;

import cn.hutool.core.codec.Base64;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.google.code.kaptcha.Producer;
import com.machine.app.iam.auth.controller.vo.response.LoongAuthCaptchaResponseVo;
import com.machine.starter.security.config.LoongCaptchaConfig;
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

import static com.machine.common.constant.LoongRedisPrefixConstant.Iam.IAM_AUTH_CAPTCHA;


@Slf4j
@RefreshScope
@RestController
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
        String captcha = producer.createText();
        BufferedImage captchaImage = producer.createImage(captcha);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(captchaImage, "jpg", outputStream);
        String base64Img = "data:image/jpeg;base64," + Base64.encode(outputStream.toByteArray());

        String userKey = IAM_AUTH_CAPTCHA + UUID.randomUUID().toString().replace("-", "");
        redisCommands.set(userKey, captcha);
        redisCommands.expire(userKey, LoongCaptchaConfig.CAPTCHA_EXPIRATION_TIME);
        log.info("获取验证码,userKey:{} captcha:{}", userKey, captcha);
        return new LoongAuthCaptchaResponseVo(userKey, base64Img);
    }

}
