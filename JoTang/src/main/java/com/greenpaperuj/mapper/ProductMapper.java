package com.greenpaperuj.mapper;

import com.greenpaperuj.pojo.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface ProductMapper {
    @Insert("insert into product(title, description, type, price, status, publisher_id) " +
            "values (#{title}, #{description}, #{type}, #{price}, #{status}, #{publisherId})")
    void insert(Product product);

    @Delete("delete from product where id = ${id}")
    void deleteById(Integer id);

    void update(Product product);

    @Select("select * from product where id = ${id}")
    Product getById(Integer id);

    @Select("select * from product")
    List<Product> list();

    List<Product> selectByCondition(String type, Double minPrice, Double maxPrice, LocalDateTime startTime, LocalDateTime endTime);
}
