package com.ikuncoffee.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "用户请求")
public class UserCreateRequest {

    @NotNull(message = "用户名不能为空")
    @Size(min = 1, max = 20, message = "用户名长度1-20位")
    @Schema(description = "用户名", example = "admin")
    private String username;

    @NotNull(message = "密码不能为空")
    @Schema(description = "密码", example = "123456")
    private String password;

    @NotNull(message = "手机号不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    @Schema(description = "手机号", example = "18888888888")
    private String phone;

}
