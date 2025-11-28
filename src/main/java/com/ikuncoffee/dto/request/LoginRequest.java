package com.ikuncoffee.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "登录")
public class LoginRequest {

    @NotNull(message = "用户名不能为空")
    @Schema(description = "用户名", example = "admin")
    private String username;

    @NotNull(message = "密码不能为空")
    @Schema(description = "密码", example = "123456")
    private String password;

}
