package com.ikuncoffee.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "用户")
public class UserCreateRequest {

    @NotNull(message = "用户名不能为空")
    @Schema(description = "用户名")
    private String username;

    @NotNull(message = "密码不能为空")
    @Schema(description = "密码")
    private String password;

    @NotNull(message = "手机号不能为空")
    @Schema(description = "手机号")
    private String phone;

}
