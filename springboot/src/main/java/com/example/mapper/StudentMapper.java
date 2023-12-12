package com.example.mapper;

import com.example.entity.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface StudentMapper {

    @Select("select * from student where username = #{username}")
    Student selectByUsername(String username);

    @Select("select * from student where email = #{email}")
    Student selectByEmail(String email);

    @Insert("insert into student (username, password, firstName, lastName, email, gender, avatar, role) " +
            "values (#{username}, #{password}, #{firstName}, #{lastName}, #{email}, #{gender}, #{avatar}, #{role})")
    void insert(Student student);

    @Delete("delete from student where id=#{id}")
    void delete(Integer id);

    @Update("update student set username=#{username}, firstName=#{firstName}," +
            "lastName=#{lastName}, phone=#{phone}, gender=#{gender}, avatar={avatar}" +
            " where id=#{id}")
    void updateById(Student student);

    @Select("select * from student where username like concat('%', #{username}, '%') and email like concat('%', #{email},'%')")
    List<Student> selectAll(Student student);

}
