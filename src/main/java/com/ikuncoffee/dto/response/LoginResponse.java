package com.ikuncoffee.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "登录")
public class LoginResponse {

    private String token;

    @Schema(description = "用户名", example = "admin")
    private String username;

    @Schema(description = "手机号", example = "1888888888")
    private String phone;
}
