package org.accountBook.controller;

import org.accountBook.common.ApiResponse;
import org.accountBook.dto.Category;
import org.accountBook.service.MoneyAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/account")
public class MoneyAccountController {

    @Autowired
    MoneyAccountService moneyAccountService;
    //增加记账分类
    @PostMapping("/addtype")
    public ApiResponse<Map<String,Object>> addAccountType(@RequestBody Category category){
        moneyAccountService.addAccountType(category);

        return ApiResponse.success(null);
    }
    //增加记账记录

    //删除记账分类

    //删除记账记录

    //计算年、月、日的开支

    //按记账分类计算开支
}
