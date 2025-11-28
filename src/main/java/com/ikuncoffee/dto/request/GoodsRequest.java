package com.ikuncoffee.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "商品")
public class GoodsRequest {

    @NotNull(message = "商品名称不能为空")
    @Schema(description = "商品名称", example = "苹果")
    private String name;

    @Schema(description = "商品描述")
    private String description;

    @NotNull(message = "商品分类ID不能为空")
    @Schema(description = "商品分类ID")
    private Long goodsCategoryId;

}
