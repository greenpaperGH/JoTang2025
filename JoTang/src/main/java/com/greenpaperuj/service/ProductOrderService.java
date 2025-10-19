package com.greenpaperuj.service;

import com.greenpaperuj.pojo.ProductOrder;

import java.time.LocalDateTime;

public interface ProductOrderService {
    void deleteById(Integer id);

    void save(ProductOrder productOrder);
}
