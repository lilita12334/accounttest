package org.accountBook.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.accountBook.utils.JwtUtil;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 跳过 OPTIONS 预检请求（CORS）
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            filterChain.doFilter(request, response);
            return;
        }


        // 获取 Authorization header
        String authHeader = request.getHeader("Authorization");
        System.out.println("Authorization Header: " + authHeader);
        String token = extractToken(request);

        if (token != null) {
            try {
                // 验证 token 并获取用户名
                String username = jwtUtil.getUsernameFromToken(token);

                // 创建简单的用户详情对象
                UserDetails userDetails = new User(
                        username,
                        "", // 密码可以为空，因为已经通过token验证
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"))
                );

                // 创建认证对象并设置到安全上下文
                org.springframework.security.authentication.UsernamePasswordAuthenticationToken authentication =
                        new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (Exception e) {
                // token 无效或过期，继续执行但不设置认证信息
                // 可以在这里记录日志
            }
        }

        // 继续过滤器链
        filterChain.doFilter(request, response);
    }

    /**
     * 从请求头中提取 JWT token
     */
    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7); // 移除 "Bearer " 前缀
        }
        return null;
    }



}
