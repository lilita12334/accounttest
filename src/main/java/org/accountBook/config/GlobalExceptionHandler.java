package org.accountBook.config;

import org.accountBook.common.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 * 功能：统一处理系统中的所有异常，返回标准的ApiResponse格式
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理所有未捕获的异常
     * @param e 异常对象
     * @return 统一的错误响应
     */
    @ExceptionHandler(Exception.class)
    public ApiResponse<String> handleException(Exception e) {
        // 记录异常信息
        logger.error("系统错误", e);
        // 返回统一的错误响应
        return ApiResponse.error("系统错误：" + e.getMessage());
    }

    /**
     * 处理非法参数异常
     * @param e 非法参数异常
     * @return 统一的错误响应
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ApiResponse<String> handleIllegalArgumentException(IllegalArgumentException e) {
        // 记录异常信息
        logger.warn("参数错误", e);
        // 返回统一的错误响应
        return ApiResponse.error(e.getMessage());
    }

    /**
     * 处理空指针异常
     * @param e 空指针异常
     * @return 统一的错误响应
     */
    @ExceptionHandler(NullPointerException.class)
    public ApiResponse<String> handleNullPointerException(NullPointerException e) {
        // 记录异常信息
        logger.error("空指针错误", e);
        // 返回统一的错误响应
        return ApiResponse.error("系统错误：空指针异常");
    }

    /**
     * 处理数组越界异常
     * @param e 数组越界异常
     * @return 统一的错误响应
     */
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ApiResponse<String> handleIndexOutOfBoundsException(IndexOutOfBoundsException e) {
        // 记录异常信息
        logger.error("数组越界错误", e);
        // 返回统一的错误响应
        return ApiResponse.error("系统错误：数组越界");
    }
}
