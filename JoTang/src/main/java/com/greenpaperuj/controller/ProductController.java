package com.greenpaperuj.controller;

import com.greenpaperuj.mapper.ProductMapper;
import com.greenpaperuj.pojo.Product;
import com.greenpaperuj.pojo.Result;
import com.greenpaperuj.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/products")
@CrossOrigin
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping
    public Result save(@RequestBody Product product) {
        log.info("新增商品信息：{}", product);
        productService.save(product);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("根据id删除产品：{}", id);
        productService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Product product) {
        log.info("更新商品信息：{}", product);
        productService.update(product);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据id查询商品信息：{}", id);
        return Result.success(productService.getById(id));
    }

    @GetMapping
    public Result list() {
        log.info("查询产品列表");
        return Result.success(productService.list());
    }

    @GetMapping("/by-condition")
    public Result selectByCondition(String type, Double minPrice, Double maxPrice,
                                    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime startTime,
                                    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime endTime) {
        log.info("根据参数查询产品订单");
        return Result.success(productService.selectByCondition(type, minPrice, maxPrice, startTime, endTime));
    }
}