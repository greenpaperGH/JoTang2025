package com.greenpaperuj.service;

import com.greenpaperuj.pojo.User;

public interface UserService {
    User login(String username, String password);

    void signup(User user);
}
