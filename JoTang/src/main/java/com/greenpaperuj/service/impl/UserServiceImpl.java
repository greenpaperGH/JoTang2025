package com.greenpaperuj.service.impl;

import com.greenpaperuj.mapper.UserMapper;
import com.greenpaperuj.pojo.User;
import com.greenpaperuj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User login(String username, String password) {
        return userMapper.selectByUsernameAndPassword(username, password);
    }

    @Override
    public void signup(User user) {
        user.setRole("USER");
        userMapper.insert(user);
    }
}
