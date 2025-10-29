package com.ikuncoffee.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ikuncoffee.dto.response.UserResponse;
import com.ikuncoffee.entity.User;

public interface UserService extends IService<User> {

    Page<UserResponse> getPage(Integer pageNum, Integer pageSize);

    UserResponse getUser(String username);

}
