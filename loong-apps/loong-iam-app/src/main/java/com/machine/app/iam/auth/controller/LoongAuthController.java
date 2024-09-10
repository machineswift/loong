package com.machine.app.iam.auth.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.captcha.generator.MathGenerator;
import cn.hutool.captcha.generator.RandomGenerator;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.machine.app.iam.auth.controller.vo.response.LoongAuthCaptchaResponseVo;
import io.lettuce.core.api.sync.RedisCommands;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

import static com.machine.common.constant.LoongRedisPrefixConstant.Iam.IAM_AUTH_CAPTCHA;


@Slf4j
@RefreshScope
@RestController
@Tag(name = "认证模块")
@RequestMapping("auth")
public class LoongAuthController {

    // 验证码有效期为5分钟
    public static final long CAPTCHA_EXPIRATION_TIME = 300;


    @Autowired
    private RedisCommands<String, String> redisCommands;

    @Operation(summary = "获取验证码")
    @ApiOperationSupport(order = 10)
    @GetMapping("captcha")
    public LoongAuthCaptchaResponseVo getVerifyCode() {
        RandomGenerator randomGenerator = new RandomGenerator("0123456789", 5);
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(120, 40);
        lineCaptcha.setGenerator(randomGenerator);

        // 生成code
        lineCaptcha.createCode();

        //获取验证码
        String captcha = lineCaptcha.getCode();

        String userKey = IAM_AUTH_CAPTCHA + UUID.randomUUID().toString().replace("-", "");
        redisCommands.set(userKey, captcha);
        redisCommands.expire(userKey, CAPTCHA_EXPIRATION_TIME);
        log.info("获取验证码,userKey:{} captcha:{}", userKey, captcha);
        return new LoongAuthCaptchaResponseVo(userKey, lineCaptcha.getImageBase64Data());
    }


    @GetMapping("image-01")
    @Operation(summary = "线段干扰的验证码")
    public void getCaptchaImage(HttpServletResponse response) throws IOException {
        //定义图形验证码的长和宽
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(200, 100);
        System.out.println("验证码：" + captcha.getCode());

        // 设置响应类型为图片
        response.setContentType("image/png");

        // 将验证码图片写入响应
        captcha.write(response.getOutputStream());
    }

    @GetMapping("image-02")
    @Operation(summary = "圆圈干扰验证码")
    public void getCaptchaImage2(HttpServletResponse response) throws IOException {
        // 创建验证码对象
        //定义图形验证码的长、宽、验证码字符数、干扰元素个数
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(200, 100, 4, 20);
        System.out.println("验证码：" + captcha.getCode());

        // 设置响应类型为图片
        response.setContentType("image/png");

        // 将验证码图片写入响应
        captcha.write(response.getOutputStream());
    }

    @GetMapping("image-03")
    @Operation(summary = "扭曲干扰验证码")
    public void getCaptchaImage3(HttpServletResponse response) throws IOException {
        //定义图形验证码的长、宽、验证码字符数、干扰线宽度
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(200, 100, 4, 4);
        System.out.println("验证码：" + captcha.getCode());

        // 设置响应类型为图片
        response.setContentType("image/png");

        // 将验证码图片写入响应
        captcha.write(response.getOutputStream());
    }

    @GetMapping("image-04")
    @Operation(summary = "自定义纯数字的验证码")
    public void getCaptchaImage4(HttpServletResponse response) throws IOException {
        //定义图形验证码的长、宽、验证码字符数、干扰线宽度
        // 自定义纯数字的验证码（随机4位数字，可重复）
        RandomGenerator randomGenerator = new RandomGenerator("0123456789", 4);
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
        lineCaptcha.setGenerator(randomGenerator);
        // 重新生成code
        lineCaptcha.createCode();
        // 设置响应类型为图片
        response.setContentType("image/png");
        // 将验证码图片写入响应
        lineCaptcha.write(response.getOutputStream());
    }

    @GetMapping("image-05")
    @Operation(summary = "加减乘除的验证码")
    public void getCaptchaImage5(HttpServletResponse response) throws IOException {
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(200, 45, 4, 4);
        // 自定义验证码内容为四则运算方式
        captcha.setGenerator(new MathGenerator(1));
        // 重新生成code
        captcha.createCode();
        MathGenerator mathGenerator = new MathGenerator();

//      用户输入校验
        System.out.println("验证结果：" + mathGenerator.verify(captcha.getCode(), "1"));


        // 设置响应类型为图片
        response.setContentType("image/png");
        // 将验证码图片写入响应
        captcha.write(response.getOutputStream());
    }

}
