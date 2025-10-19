package com.greenpaperuj.service;

import com.greenpaperuj.pojo.Product;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductService {
    void save(Product product);

    void delete(Integer id);

    void update(Product product);

    Product getById(Integer id);

    List<Product> list();

    List<Product> selectByCondition(String type, Double minPrice, Double maxPrice, LocalDateTime startTime, LocalDateTime endTime);
}
