package com.machine.starter.security;

import io.jsonwebtoken.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Map;

@Data
@Component
@RefreshScope
public class LoongJwtUtil {

    @Value("${loong.jwt.expire:7}")
    private long expire;

    private final String SECRET = "S/4AN9IsSRUC~{0c4]y#$F2XbV8^`#a14vawn<~Kr@(D%3TF-p1s/h{Y9k7y((rR";
    private final SecretKey key = Jwts.SIG.HS512.key().random(new SecureRandom(SECRET.getBytes(StandardCharsets.UTF_8))).build();

    /**
     * 生成JWT
     */
    public String generateToken(String username) {
        return generateToken(username, null);
    }

    /**
     * 生成JWT
     */
    public String generateToken(String username, Map<String, Object> claims) {
        return generateToken(username, claims, expire);
    }

    /**
     * 生成JWT
     */
    public String generateToken(String username, Map<String, Object> claims, long expire) {
        JwtBuilder builder = Jwts.builder();
        Date now = new Date();
        // 生成token
        builder.id("rQRk$yN:7%*Bw}A_A-]M~4#;yGa:a_F{") //id 这个可以不填，但是建议填
                .claims(claims) //数据
                .issuer("machine") //签发者
                .subject(username) //主题
                .issuedAt(now) //签发时间
                .expiration(new Date(System.currentTimeMillis() + expire * 24 * 60 * 60 * 1000)) //过期时间
                .signWith(key); //签名方式
        builder.header().add("type", "JWT").add("alg", "HS512");
        return builder.compact();
    }


    /**
     * 解析JWT
     */
    public Claims getClaimsByToken(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (Exception e) {
            if (e instanceof ExpiredJwtException) {
                throw new RuntimeException("token已过期");
            }
            if (e instanceof JwtException) {
                throw new RuntimeException("token已失效");
            }
            throw new RuntimeException("token解析失败");
        }
    }
}