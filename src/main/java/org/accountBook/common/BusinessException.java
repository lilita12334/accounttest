package org.accountBook.common;  // 放在这里，和 ApiResponse 做邻居

/**
 * 自定义业务异常
 * 用于处理：密码错误、余额不足、删除失败、数据不存在等业务规则失败
 * 注意：不需要加 @Component 等任何 Spring 注解！
 */
public class BusinessException extends RuntimeException {

    private final Integer code;
    private final String msg;

    // 构造方法1：只传错误信息（默认错误码 400）
    public BusinessException(String msg) {
        super(msg);  // 把信息传给父类 RuntimeException
        this.code = 400;
        this.msg = msg;
    }

    // 构造方法2：传错误码 + 错误信息（更灵活）
    public BusinessException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    // Getter 方法（让全局异常处理器能取到 code 和 msg）
    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}