package com.ikuncoffee.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ikuncoffee.dto.response.OrderResponse;
import com.ikuncoffee.dto.response.UserResponse;
import com.ikuncoffee.entity.Order;
import com.ikuncoffee.entity.User;
import com.ikuncoffee.mapper.OrderMapper;
import com.ikuncoffee.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Override
    public Page<OrderResponse> getPage(Integer pageNum, Integer pageSize) {

        Page<Order> page = new Page<>(pageNum, pageSize);
        IPage<Order> result = this.page(page);

        Page<OrderResponse> orderResponsePage = new Page<>();
        orderResponsePage.setCurrent(pageNum);
        orderResponsePage.setSize(pageSize);
        orderResponsePage.setTotal(result.getTotal());
        orderResponsePage.setPages(result.getPages());
        List<OrderResponse> orderResponseList = page.getRecords().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        orderResponsePage.setRecords(orderResponseList);
        return orderResponsePage;

    }


    private OrderResponse convertToResponse(Order order) {
        OrderResponse orderResponse = new OrderResponse();
        BeanUtils.copyProperties(order, orderResponse);
        return orderResponse;
    }

}
