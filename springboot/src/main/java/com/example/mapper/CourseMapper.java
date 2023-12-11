package com.example.mapper;

import com.example.entity.Course;
import org.apache.ibatis.annotations.Select;

import javax.annotation.Resource;
import java.util.List;

public interface CourseMapper {

    @Select("select * from course order by id desc")
    List<Course> selectAll();
}
