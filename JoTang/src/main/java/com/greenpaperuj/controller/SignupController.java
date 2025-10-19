package com.greenpaperuj.controller;

import com.greenpaperuj.pojo.Result;
import com.greenpaperuj.pojo.User;
import com.greenpaperuj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/signup")
@CrossOrigin
public class SignupController {
    @Autowired
    UserService userService;

    @PostMapping
    public Result signup(@RequestBody User user) {
        log.info("新增用户：{}", user);
        userService.signup(user);
        return Result.success();
    }
}
