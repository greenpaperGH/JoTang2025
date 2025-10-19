package com.greenpaperuj.controller;

import com.greenpaperuj.pojo.ProductOrder;
import com.greenpaperuj.pojo.Result;
import com.greenpaperuj.service.ProductOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/product-orders")
@CrossOrigin
public class ProductOrderController {
    @Autowired
    ProductOrderService productOrderService;

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id) {
        log.info("根据id删除订单：{}", id);
        productOrderService.deleteById(id);
        return Result.success();
    }

    @PostMapping
    public Result save(@RequestBody ProductOrder productOrder) {
        log.info("新增订单信息：{}", productOrder);
        productOrderService.save(productOrder);
        return Result.success();
    }
}
