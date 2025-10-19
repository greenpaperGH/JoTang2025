package com.greenpaperuj.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private String role;
    private String phone;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
