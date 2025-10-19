package com.greenpaperuj.mapper;

import com.greenpaperuj.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from user where username = #{username} and password = #{password}")
    User selectByUsernameAndPassword(String username, String password);

    @Insert("insert into user (username, password, role, phone) values " +
            "(#{username}, #{password}, #{role}, #{phone})")
    void insert(User user);
}
