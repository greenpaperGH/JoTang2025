package com.greenpaperuj.mapper;

import com.greenpaperuj.pojo.ProductOrder;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductOrderMapper {
    @Delete("delete from product_order where id = #{id}")
    void deleteById(Integer id);

    @Insert("insert into product_order (product_id, buyer_id, status) values " +
            "(#{productId}, #{buyerId}, #{status})")
    void insert(ProductOrder productOrder);
}
