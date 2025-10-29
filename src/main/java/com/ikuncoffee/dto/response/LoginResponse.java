package com.ikuncoffee.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "登录")
public class LoginResponse {
    private String token;
    private String username;
    private String phone;
}
