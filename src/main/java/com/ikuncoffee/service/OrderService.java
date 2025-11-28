package com.ikuncoffee.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ikuncoffee.dto.response.OrderResponse;
import com.ikuncoffee.entity.Order;

public interface OrderService extends IService<Order> {

    Page<OrderResponse> getPage(Integer pageNum, Integer pageSize);

}
