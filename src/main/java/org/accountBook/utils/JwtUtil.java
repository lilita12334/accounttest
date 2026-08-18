package org.accountBook.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    // 生产环境应从配置文件读取（Base64 编码的密钥）
    // 生成方法：使用在线工具生成 32+ 字符的随机字符串，然后 Base64 编码
    // 例如："ThisIsA32CharacterSecretKey123456!" → Base64 编码
    private static final String SECRET_KEY = "VGhpc0lzQTMyQ2hhcmFjdGVyU2VjcmV0S2V5MTIzNDU2IQ==";
    // 解码后是："ThisIsA32CharacterSecretKey123456!" (32 字符)

    // 7 天有效期
    private static final long EXPIRATION_TIME = 7L * 24 * 60 * 60 * 1000;

    private static SecretKey getSignInKey() {
        // 使用 BASE64 解码器解码密钥
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * 生成 JWT token
     */
    public String generateToken(Long userId, String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("username", username);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public static void main(String[] args) {
        String SECRET_KEY = "VGhpc0lzQTMyQ2hhcmFjdGVyU2VjcmV0S2V5MTIzNDU2IQ==";
        long EXPIRATION_TIME = 7L * 24 * 60 * 60 * 1000;

        System.out.println("========== JWT Token 生成器（有效期7天）==========\n");

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", 1L);
        claims.put("username", "admin");

        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        SecretKey key = Keys.hmacShaKeyFor(keyBytes);

        String adminToken = Jwts.builder()
                .setClaims(claims)
                .setSubject("admin")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        System.out.println("【管理员 Token】");
        System.out.println("用户ID: 1");
        System.out.println("用户名: admin");
        System.out.println("完整Header: Authorization: Bearer " + adminToken);
        System.out.println();

        System.out.println("================================");
    }
    /**
     * 解析 JWT token
     */
    public Claims parseToken(String token) {
        if (token == null || token.isEmpty()) {
            throw new IllegalArgumentException("Token cannot be null or empty");
        }

        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 从 token 中获取用户名
     */
    public String getUsernameFromToken(String token) {
        Claims claims = parseToken(token);
        return claims.get("username", String.class);
    }

    /**
     * 从 token 中获取用户 ID
     */
    public Long getUserIdFromToken(String token) {
        Claims claims = parseToken(token);
        return claims.get("userId", Long.class);
    }

//    // 新增的，Controller 用 —— 这是给业务代码调的
//    public Long getCurrentUserId() {
//        return (Long) SecurityContextHolder.getContext()
//                .getAuthentication()
//                .getPrincipal();
//    }
}
