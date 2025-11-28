package com.ikuncoffee.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ikuncoffee.dto.request.RemoveRequest;
import com.ikuncoffee.dto.request.UserCreateRequest;
import com.ikuncoffee.dto.response.ApiResponse;
import com.ikuncoffee.dto.response.UserResponse;
import com.ikuncoffee.entity.User;
import com.ikuncoffee.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@Tag(name="用户管理", description = "用户相关操作")
@Slf4j
public class UserController {

    private final UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "获取用户列表", description = "获取全部用户")
    @GetMapping("query")
    public ApiResponse<List<UserResponse>> findAll() {
        List<User> userList = userService.list();

        List<UserResponse> userResponseList = userList.stream().map(this::convertToResponse).collect(Collectors.toList());

        return ApiResponse.success(userResponseList);
    }

    @Operation(summary = "获取用户分页", description = "分页获取用户")
    @GetMapping
    public ApiResponse<IPage<UserResponse>> page(Integer pageNum, Integer pageSize) {

        Page<UserResponse> result = userService.getPage(pageNum, pageSize);

        return ApiResponse.success(result);
    }


    @Operation(summary = "新增用户")
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

    @Operation(summary = "删除用户")
    @PostMapping("/delete")
    public ApiResponse<Boolean> remove(
            @Validated @RequestBody RemoveRequest request
    ) {
        boolean result = userService.removeById(request.getId());
        if (!result) {
            throw new RuntimeException("删除用户失败");
        }
        return ApiResponse.success(result);
    }

    private UserResponse convertToResponse(User user) {
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(user, userResponse);
        return userResponse;
    }
}
