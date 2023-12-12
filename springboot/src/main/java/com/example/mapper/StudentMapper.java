package com.example.mapper;

import com.example.entity.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface StudentMapper {

    @Select("select * from student where username = #{username} and status=1")
    Student selectByUsername(String username);

    @Select("select * from student where email = #{email} and status=1")
    Student selectByEmail(String email);

    @Select("select * from student where id = #{id} and status=1")
    Student selectById(Integer id);

    @Insert("insert into student (username, password, firstName, lastName, email, gender, avatar, role) " +
            "values (#{username}, #{password}, #{firstName}, #{lastName}, #{email}, #{gender}, #{avatar}, #{role})")
    void insert(Student student);

    @Delete("delete from student where id=#{id}")
    void delete(Integer id);

    @Update("update student set username=#{username}, password=#{password}, firstName=#{firstName}, lastName=#{lastName}, " +
            "email=#{email}, phone=#{phone}, gender=#{gender}, avatar=#{avatar} where id=#{id}")
    void updateById(Student student);

    @Select("select * from student where username like concat('%', #{username}, '%')" +
            " and firstName like concat('%', #{firstName},'%') and lastName like concat('%', #{lastName},'%') and status=1")
    List<Student> selectAll(Student student);

    @Update("update student set status=0 where id = #{id}")
    void pseudoDeleteById(Integer id);

}
