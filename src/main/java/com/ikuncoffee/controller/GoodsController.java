package com.ikuncoffee.controller;

import com.ikuncoffee.dto.request.GoodsRequest;
import com.ikuncoffee.dto.response.ApiResponse;
import com.ikuncoffee.dto.response.GoodsResponse;
import com.ikuncoffee.entity.Goods;
import com.ikuncoffee.service.GoodsService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    private final GoodsService goodsService;

    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @GetMapping
    public ApiResponse<GoodsResponse> getGoods(){
        return ApiResponse.success(null);
    }

    @PostMapping
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
