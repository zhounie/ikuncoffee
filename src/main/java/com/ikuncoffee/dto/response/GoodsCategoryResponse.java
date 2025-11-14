package com.ikuncoffee.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "商品分类")
public class GoodsCategoryResponse {

    private Long id;
    private String name;
    private String description;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
