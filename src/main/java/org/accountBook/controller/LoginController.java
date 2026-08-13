package org.accountBook.controller;

import lombok.extern.slf4j.Slf4j;
import org.accountBook.common.ApiResponse;
import org.accountBook.dto.LoginRequest;
import org.accountBook.model.User;
import org.accountBook.service.LoginService;
import org.accountBook.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController//向前端返回json的必要注解
@RequestMapping("login")
public class LoginController {

    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    LoginService loginService;
    @PostMapping("/user")
    public ApiResponse<Map<String,Object>> login(@RequestBody LoginRequest login){
        User u=loginService.loginToken(login);
        if (u!=null){
            // 生成 JWT token（使用 JwtUtil）
            String token = jwtUtil.generateToken(u.getId(), u.getUsername());

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", Map.of(
                "id", u.getId(),
                "username", u.getUsername()
        ));
            return ApiResponse.success(null);
        }

                //有空补个token
        return ApiResponse.error("用户出错");
    }
}
