package com.example.mapper;

import com.example.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper {

    @Select("select * from user where username = #{username} and status=1")
    User selectByUsername(String username);

    @Select("select * from user where email = #{email} and status=1")
    User selectByEmail(String email);

    @Select("select * from user where id = #{id} and status=1")
    User selectById(Integer id);

    @Insert("insert into user (username, password, firstName, lastName, email, gender, avatar, role) " +
            "values (#{username}, #{password}, #{firstName}, #{lastName}, #{email}, #{gender}, #{avatar}, #{role})")
    void insert(User user);

    @Delete("delete from user where id=#{id}")
    void delete(Integer id);

    @Update("update user set username=#{username}, password=#{password}, firstName=#{firstName}, lastName=#{lastName}, " +
            "email=#{email}, phone=#{phone}, gender=#{gender}, avatar=#{avatar} where id=#{id}")
    void updateById(User user);

    @Select("select * from user where username like concat('%', #{username}, '%')" +
            " and firstName like concat('%', #{firstName},'%') and lastName like concat('%', #{lastName},'%') and status=1")
    List<User> selectAll(User user);

    @Update("update user set status=0 where id = #{id}")
    void pseudoDeleteById(Integer id);

}
