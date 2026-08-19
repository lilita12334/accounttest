package org.accountBook.controller;

import org.accountBook.common.ApiResponse;
import org.accountBook.common.UserContext;
import org.accountBook.dto.Account;
import org.accountBook.dto.Category;
import org.accountBook.service.MoneyAccountService;
import org.accountBook.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class MoneyAccountController {

    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    MoneyAccountService moneyAccountService;
    //增加记账分类
    @PostMapping("/addtype")
    public ApiResponse<Map<String,Object>> addAccountType(@RequestBody Category category){
        category.setUser_id(UserContext.getCurrentUserId());
        moneyAccountService.addAccountType(category);

        return ApiResponse.success(null);
    }

    //获取分类

    @GetMapping("/getcategory")
    public ApiResponse<List<Category>> getCategory(){
        Long userId=UserContext.getCurrentUserId();
List<Category> type=moneyAccountService.getCategory(userId);
        return ApiResponse.success(type);

    }

    //获取记账
    @GetMapping("/getAccount")
    public ApiResponse<List<Account>> getAccount(){


        List<Account> list= moneyAccountService.getAccount();
        return ApiResponse.success(list);
    }
    //增加记账记录

    @PostMapping("/addAccount")
    public ApiResponse<Map<String,Object>> AddAcount(@RequestBody Account account ){
        account.setUser_id(UserContext.getCurrentUserId());
        moneyAccountService.addAccount(account);
        return ApiResponse.success(null);
    }
    //删除记账分类
    @DeleteMapping("/detype")
    public ApiResponse<Map<String,Object>> DeleteType(@RequestParam("id") Long id){
        Long userId = UserContext.getCurrentUserId();
        moneyAccountService.deleteType(id,userId);
        return ApiResponse.success(null);
    }

    //删除记账记录
    @DeleteMapping("/deaccount")
    public ApiResponse<Map<String,Object>> deleteAccount( Long accountId){
        moneyAccountService.deleteAccount(accountId);
        return ApiResponse.success(null);
    }

    //计算年、月、日的开支

    //按记账分类计算开支
}
