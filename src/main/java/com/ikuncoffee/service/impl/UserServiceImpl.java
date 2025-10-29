package com.ikuncoffee.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ikuncoffee.dto.response.UserResponse;
import com.ikuncoffee.entity.User;
import com.ikuncoffee.mapper.UserMapper;
import com.ikuncoffee.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean save(User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, user.getUsername());
        Long count = userMapper.selectCount(queryWrapper);
        if (count > 0) {
            throw new RuntimeException("用户名已存在");
        }
        // 确保密码按照系统的 PasswordEncoder 进行加密存储
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return super.save(user);
    }

    public Page<UserResponse> getPage(Integer pageNum, Integer pageSize) {

        Page<User> page = new Page<>(pageNum, pageSize);

        IPage<User> result = this.page(page);

        Page<UserResponse> userResponsePage = new Page<>();

        userResponsePage.setCurrent(pageNum);
        userResponsePage.setSize(pageSize);
        userResponsePage.setTotal(result.getTotal());
        userResponsePage.setPages(result.getPages());
        List<UserResponse> userResponseList = page.getRecords().stream()
                                                .map(this::convertToResponse)
                                                .collect(Collectors.toList());
        userResponsePage.setRecords(userResponseList);
        return userResponsePage;
    }

    public UserResponse getUser(String username) {
        User user = userMapper.findByUsername(username);
        return this.convertToResponse(user);
    }

    private UserResponse convertToResponse(User user) {
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(user, userResponse);
        return userResponse;
    }

}
