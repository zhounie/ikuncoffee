package com.ikuncoffee.controller;

import com.ikuncoffee.dto.request.GoodsCategoryRequest;
import com.ikuncoffee.dto.response.ApiResponse;
import com.ikuncoffee.dto.response.GoodsCategoryResponse;
import com.ikuncoffee.entity.GoodsCategory;
import com.ikuncoffee.service.GoodsCategoryService;
import jakarta.validation.Valid;
import jdk.jfr.Category;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/goodsCategory")
public class GoodsCategoryController {

    private final GoodsCategoryService goodsCategoryService;

    public GoodsCategoryController(GoodsCategoryService goodsCategoryService) {
        this.goodsCategoryService = goodsCategoryService;
    }

    @GetMapping
    public ApiResponse<GoodsCategoryResponse> getGoodsCategory() {
        return new ApiResponse<>();
    }

    @PostMapping
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
