package com.ikuncoffee.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ikuncoffee.dto.request.OrderRequest;
import com.ikuncoffee.dto.response.ApiResponse;
import com.ikuncoffee.dto.response.OrderResponse;
import com.ikuncoffee.entity.Order;
import com.ikuncoffee.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/order")
@Tag(name = "订单管理", description = "订单相关操作")
@Slf4j
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(summary = "获取订单分页", description = "分页获取订单")
    @GetMapping
    public ApiResponse<IPage<OrderResponse>> page(Integer pageNum, Integer pageSize) {
        Page<OrderResponse> result = orderService.getPage(pageNum, pageSize);

        return ApiResponse.success(result);
    }

    @Operation(summary = "新增订单", description = "获取订单")
    @PostMapping
    public ApiResponse<Boolean> save(@Valid @RequestBody OrderRequest orderRequest) {
        Order order = new Order();
        BeanUtils.copyProperties(orderRequest, order);

//        order.setOrderNo(new Date().getTime() + "" + Math.random());

        boolean result = orderService.save(order);
        if (!result) {
            throw new RuntimeException("创建订单失败");
        }
        return ApiResponse.success(result);
    }
}
