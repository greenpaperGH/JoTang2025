package com.greenpaperuj.service.impl;

import com.greenpaperuj.mapper.ProductOrderMapper;
import com.greenpaperuj.pojo.ProductOrder;
import com.greenpaperuj.service.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductOrderServiceImpl implements ProductOrderService {
    @Autowired
    ProductOrderMapper productOrderMapper;

    @Override
    public void deleteById(Integer id) {
        productOrderMapper.deleteById(id);
    }

    @Override
    public void save(ProductOrder productOrder) {
        productOrderMapper.insert(productOrder);
    }
}
