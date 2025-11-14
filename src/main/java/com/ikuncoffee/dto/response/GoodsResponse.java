package com.ikuncoffee.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "商品")
public class GoodsResponse {

    private Long id;

    private Long goodsCategoryId;

    private String name;

    private String description;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
