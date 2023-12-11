package com.example.mapper;

import com.example.entity.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface StudentMapper {

    @Select("select * from student where username = #{username}")
    Student selectByUsername(String username);

    @Select("select * from student where email = #{email}")
    Student selectByEmail(String email);

    @Insert("insert into student (username, password, first_name, last_name, email, gender, avatar, role) " +
            "values (#{username}, #{password}, #{first_name}, #{last_name}, #{email}, #{gender}, #{avatar}, #{role})")
    void insert(Student student);

}
