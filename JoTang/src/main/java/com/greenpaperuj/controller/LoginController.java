package com.greenpaperuj.controller;

import com.greenpaperuj.pojo.Result;
import com.greenpaperuj.pojo.User;
import com.greenpaperuj.service.UserService;
import com.greenpaperuj.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController {
    @Autowired
    UserService userService;

    @PostMapping
    public Result login(String username, String password) {
        log.info("根据用户名和密码登录：{},{}",username, password);
        User user;
        user = userService.login(username, password);
        if(user != null) {
            Map<String, Object> claims = new LinkedHashMap<>();
            claims.put("id", user.getId());
            claims.put("username", user.getUsername());
            claims.put("role", user.getRole());
            return Result.success(JwtUtils.generateJwt(claims));
        }
        return Result.error("Failed to login.");
    }
}
