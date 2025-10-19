package com.greenpaperuj.service.impl;

import com.greenpaperuj.mapper.ProductMapper;
import com.greenpaperuj.pojo.Product;
import com.greenpaperuj.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public void save(Product product) {
        productMapper.insert(product);
    }

    @Override
    public void delete(Integer id) {
        productMapper.deleteById(id);
    }

    @Override
    public void update(Product product) {
        product.setUpdateTime(LocalDateTime.now());
        productMapper.update(product);
    }

    @Override
    public Product getById(Integer id) {
        return productMapper.getById(id);
    }

    @Override
    public List<Product> list() {
        return productMapper.list();
    }

    @Override
    public List<Product> selectByCondition(String type, Double minPrice, Double maxPrice, LocalDateTime startTime, LocalDateTime endTime) {
        return productMapper.selectByCondition(type, minPrice, maxPrice, startTime, endTime);
    }
}
