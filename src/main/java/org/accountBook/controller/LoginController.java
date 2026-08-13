package org.accountBook.controller;

import org.accountBook.common.ApiResponse;
import org.accountBook.dto.LoginRequest;
import org.accountBook.model.User;
import org.accountBook.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController//向前端返回json的必要注解
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    LoginService loginService;
    @PostMapping("/user")
    public ApiResponse<Map<String,Object>> login(@RequestBody LoginRequest login){
        User u=loginService.loginToken(login);
        if (u!=null)
            return ApiResponse.success(null);
                //有空补个token
        return ApiResponse.error("用户出错");
    }
}
