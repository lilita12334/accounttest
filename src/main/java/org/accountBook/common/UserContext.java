package org.accountBook.common;

/**
 * 用户上下文工具类
 * 用于在单次请求中存储当前登录用户的 ID
 */
public class UserContext {

    private static final ThreadLocal<Long> USER_ID = new ThreadLocal<>();

    // 设置（由 JwtFilter 调用）
    public static void setUserId(Long userId) {
        USER_ID.set(userId);
    }

    // 获取（由 Controller / Service 调用）
    public static Long getCurrentUserId() {
        Long userId = USER_ID.get();
        if (userId == null) {
            throw new BusinessException("用户未登录或登录已过期");
        }
        return userId;
    }

    // 清除（防止内存泄漏，在过滤器中调用）
    public static void clear() {
        USER_ID.remove();
    }
}