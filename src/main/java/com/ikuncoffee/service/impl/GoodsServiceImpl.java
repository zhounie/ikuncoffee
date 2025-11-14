package com.ikuncoffee.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ikuncoffee.entity.Goods;
import com.ikuncoffee.mapper.GoodsMapper;
import com.ikuncoffee.service.GoodsService;
import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {
}
