package org.accountBook.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class LoginRequest {
    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    //@Size(min = 6, message = "密码长度不能少于6位")
    private String password;

}
