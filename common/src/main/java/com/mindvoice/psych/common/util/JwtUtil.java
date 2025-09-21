package com.mindvoice.psych.common.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {
    private static final long EXPIRATION = 86400000; // 1天
    private static final String SECRET = "my-super-secret-key-which-is-at-least-32-bytes";

    // Key 对象
    private static final Key KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    // 生成 token
    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600_000)) // 1小时
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    // 解析 token
    public static String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}