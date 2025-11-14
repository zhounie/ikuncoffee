package com.ikuncoffee.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ikuncoffee.entity.GoodsCategory;
import com.ikuncoffee.mapper.GoodsCategoryMapper;
import com.ikuncoffee.service.GoodsCategoryService;
import org.springframework.stereotype.Service;

@Service
public class GoodsCategoryServiceImpl extends ServiceImpl<GoodsCategoryMapper, GoodsCategory> implements GoodsCategoryService {

}
