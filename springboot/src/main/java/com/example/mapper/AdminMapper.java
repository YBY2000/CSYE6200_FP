package com.example.mapper;

import com.example.entity.Admin;
import org.apache.ibatis.annotations.Select;

import javax.annotation.Resource;

public interface AdminMapper {

    @Select("select * from admin where username = #{username}")
    Admin selectByUsername(String username);
}
