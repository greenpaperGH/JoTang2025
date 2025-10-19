package com.greenpaperuj.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Product {
    private Long id;
    private String title;
    private String description;
    private String type;
    private Double price;
    private String status;
    private Long publisherId;
    private LocalDateTime publishTime;
    private LocalDateTime updateTime;
}
