package com.ikuncoffee.controller;

import com.ikuncoffee.dto.request.GoodsCategoryRequest;
import com.ikuncoffee.dto.response.ApiResponse;
import com.ikuncoffee.dto.response.GoodsCategoryResponse;
import com.ikuncoffee.entity.GoodsCategory;
import com.ikuncoffee.service.GoodsCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jdk.jfr.Category;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/goodsCategory")
@Tag(name="商品分类管理", description = "商品分类相关操作")
public class GoodsCategoryController {

    private final GoodsCategoryService goodsCategoryService;

    public GoodsCategoryController(GoodsCategoryService goodsCategoryService) {
        this.goodsCategoryService = goodsCategoryService;
    }

    @GetMapping
    @Operation(summary = "获取全部商品分类")
    public ApiResponse<GoodsCategoryResponse> getGoodsCategory() {
        return new ApiResponse<>();
    }

    @PostMapping
    @Operation(summary = "创建商品分类")
    public ApiResponse<Boolean> save(@Valid @RequestBody GoodsCategoryRequest request) {

        GoodsCategory goodsCategory = new GoodsCategory();
        BeanUtils.copyProperties(request, goodsCategory);
        boolean result = goodsCategoryService.save(goodsCategory);
        if (!result) {
            throw new RuntimeException("创建分类失败");
        }
        return ApiResponse.success(result);
    }

}
