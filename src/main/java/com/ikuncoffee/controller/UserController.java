package com.ikuncoffee.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ikuncoffee.dto.request.UserCreateRequest;
import com.ikuncoffee.dto.response.ApiResponse;
import com.ikuncoffee.dto.response.UserResponse;
import com.ikuncoffee.entity.User;
import com.ikuncoffee.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("query")
    public ApiResponse<List<User>> findAll() {
        List<User> userList = userService.list();

        return ApiResponse.success(userList);
    }

    @GetMapping
    public ApiResponse<IPage<UserResponse>> page(Integer pageNum, Integer pageSize) {

        Page<UserResponse> result = userService.getPage(pageNum, pageSize);

        return ApiResponse.success(result);
    }

    @PostMapping
    public ApiResponse<Boolean> save(
            @Validated @RequestBody UserCreateRequest request
    ) {
        User user = new User();
        BeanUtils.copyProperties(request, user);



        boolean result = userService.save(user);
        if (!result) {
            throw new RuntimeException("创建用户失败");
        }
        return ApiResponse.success(result);
    }
}
