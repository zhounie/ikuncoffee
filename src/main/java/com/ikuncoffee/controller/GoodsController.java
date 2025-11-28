package com.ikuncoffee.controller;

import com.ikuncoffee.dto.request.GoodsRequest;
import com.ikuncoffee.dto.response.ApiResponse;
import com.ikuncoffee.dto.response.GoodsResponse;
import com.ikuncoffee.entity.Goods;
import com.ikuncoffee.service.GoodsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/goods")
@Tag(name="商品管理", description = "商品相关操作")
public class GoodsController {

    private final GoodsService goodsService;

    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @GetMapping
    @Operation(summary = "获取商品列表", description = "获取全部商品")
    public ApiResponse<GoodsResponse> getGoods(){
        return ApiResponse.success(null);
    }

    @PostMapping
    @Operation(summary = "新增商品", description = "创建商品")
    public ApiResponse<Boolean> save(@Valid @RequestBody GoodsRequest request){

        Goods goods = new Goods();
        BeanUtils.copyProperties(request, goods);
        boolean result = goodsService.save(goods);
        if (!result){
            throw new RuntimeException("创建商品失败");
        }
        return ApiResponse.success(result);
    }
}
