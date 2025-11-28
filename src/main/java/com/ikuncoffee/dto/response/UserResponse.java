package com.ikuncoffee.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "用户响应")
public class UserResponse {

    @Schema(description = "用户ID", example = "1")
    private Long id;

    @Schema(description = "用户名", example = "admin")
    private String username;

    @Schema(description = "手机号", example = "18888888888")
    private String phone;

    @Schema(description = "创建时间", example = "2025-01-01 00:00:00")
    private LocalDateTime createTime;

    @Schema(description = "更新时间", example = "2025-01-01 00:00:00")
    private LocalDateTime updateTime;
}
