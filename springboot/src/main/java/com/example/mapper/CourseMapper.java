package com.example.mapper;

import com.example.entity.Course;
import org.apache.ibatis.annotations.Select;

import javax.annotation.Resource;
import java.util.List;

public interface CourseMapper {

    @Select("select * from course where number like concat('%',#{number}, '%') " +
            "or subject like concat('%',#{subject}, '%') " +
            "or campus like concat('%',#{campus}, '%') order by id desc")
    List<Course> selectAll(String number, String subject, String campus);
}
