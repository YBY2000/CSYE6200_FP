package com.example.service;

import com.example.entity.Course;
import com.example.mapper.CourseMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CourseService {

    @Resource
    private CourseMapper courseMapper;

    /**
     * total: total number of search
     * list: data list
     * @param pageNum current page number
     * @param pageSize size of every page
     * @return the list of data
     */

    public PageInfo<Course> selectPage(Integer pageNum, Integer pageSize, String courseNum, String subject, String campus){
        PageHelper.startPage(pageNum, pageSize);
        List<Course> courseList = courseMapper.selectAll(courseNum, subject, campus);
        return PageInfo.of(courseList);
    }
}
