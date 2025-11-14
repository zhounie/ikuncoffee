package com.ikuncoffee.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "商品分类")
public class GoodsCategoryRequest {

    @NotNull(message = "分类名称不能为空")
    @Schema(description = "分类名称")
    private String name;

    @Schema(description = "分类描述")
    private String description;

}
