package com.greenpaperuj.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductOrder {
    private Long id;
    private Long productId;
    private Long buyerId;
    private String status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
