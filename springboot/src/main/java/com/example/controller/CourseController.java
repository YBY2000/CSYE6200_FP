package com.example.controller;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.Result;
import com.example.entity.Course;
import com.example.service.CourseService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Resource
    private CourseService courseService;

    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize,
                             @RequestParam String courseNum,
                             @RequestParam String subject,
                             @RequestParam String campus) {
        PageInfo<Course> coursePageInfo = courseService.selectPage(pageNum, pageSize, courseNum, subject, campus);
        return Result.success(coursePageInfo);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Course course) {
        if (ObjectUtil.isEmpty(course.getCampus()) ||
                ObjectUtil.isEmpty(course.getDescription()) ||
                ObjectUtil.isEmpty(course.getHours()) ||
                ObjectUtil.isEmpty(course.getLocation()) ||
                ObjectUtil.isEmpty(course.getNumber()) ||
                ObjectUtil.isEmpty(course.getSection()) ||
                ObjectUtil.isEmpty(course.getTimetable()) ||
                ObjectUtil.isEmpty(course.getInstructor()) ||
                ObjectUtil.isEmpty(course.getTitle()) ||
                ObjectUtil.isEmpty(course.getSubject())){
            return Result.error("Fill in all area!");
        }
        courseService.add(course);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody Course course) {
        courseService.updateById(course);
        return Result.success();
    }

    /**
     * use this pseudo delete normally
     * @param id the data id
     * @return succeed message
     */
    @PutMapping("/pseudo-delete/{id}")
    public Result pseudoDelete(@PathVariable Integer id) {
        courseService.pseudoDeleteById(id);
        return Result.success();
    }

    /**
     * permanently delete data, use it CAREFULLY!
     * @param id the data id
     * @return succeed message
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        courseService.delete(id);
        return Result.success();
    }

}
