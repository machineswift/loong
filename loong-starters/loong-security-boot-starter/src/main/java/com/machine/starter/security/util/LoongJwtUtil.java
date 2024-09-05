package com.machine.starter.security.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@RefreshScope
public class LoongJwtUtil {

    @Value("${loong.jwt.expire:7}")
    private long expire;

    @Value("${loong.jwt.secret:QaxWsxRtueyibcjsckabajchhhyfhgvhg2@2glmhtUhgGNHvbbsdcsjhvGuyuhvgvhh}")
    private String secret;

    public static final String HEADER_STRING = "Authorization";


    /**
     * 生成JWT
     */
    public String generateToken(String username) {
        return generateToken(username, null);
    }

    /**
     * 生成JWT
     */
    public String generateToken(String username, String dataKey, String dataValue) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(dataKey, dataValue);
        return generateToken(username, claims);
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
        if (null == KEY) {
            KEY = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        }

        JwtBuilder builder = Jwts.builder();
        Date now = new Date();
        // 生成token
        builder.id(UUID.randomUUID().toString().replace("-", ""))
                .claims(claims) //数据
                .issuer("machine") //签发者
                .subject(username) //主题
                .issuedAt(now) //签发时间
                .expiration(new Date(System.currentTimeMillis() + expire * 24 * 60 * 60 * 1000)) //过期时间
                .signWith(KEY); //签名方式
        builder.header().add("type", "JWT").add("alg", "HS512");
        return builder.compact();
    }


    /**
     * 解析JWT
     */
    public Claims getClaimsByToken(String token) {
        if (null == KEY) {
            KEY = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        }

        try {
            return Jwts.parser()
                    .setSigningKey(KEY)
                    //.verifyWith((SecretKey) KEY)
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

    private Key KEY;
}