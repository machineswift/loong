package com.machine.app.iam.auth.controller;

import com.google.code.kaptcha.Producer;
import com.machine.app.iam.auth.controller.vo.request.LoginCredentialsRequestVo;
import com.machine.app.iam.config.CaptchaConfig;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Slf4j
@RestController
@RefreshScope
@RequestMapping("auth")
public class LoongAuthController {

    @Autowired
    private Producer producer;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("vc")
    public void getVerifyCode(HttpServletResponse resp,
                              HttpSession session) throws IOException {
        resp.setContentType("image/jpeg");
        String text = producer.createText();
        session.setAttribute("captcha", text);
        session.setAttribute("captchaExpirationTime",
                LocalDateTime.now().plus(CaptchaConfig.CAPTCHA_EXPIRATION_TIME, ChronoUnit.SECONDS));
        BufferedImage image = producer.createImage(text);
        try (ServletOutputStream out = resp.getOutputStream()) {
            ImageIO.write(image, "jpg", out);
        }
    }

}
