package com.example.controller;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.Result;
import com.example.entity.Student;
import com.example.service.StudentService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    StudentService studentService;


    @PostMapping("/add")
    public Result add(@RequestBody Student student) {
        if (ObjectUtil.isEmpty(student.getUsername()) ||
                ObjectUtil.isEmpty(student.getPassword()) ||
                ObjectUtil.isEmpty(student.getLastName()) ||
                ObjectUtil.isEmpty(student.getFirstName()) ||
                ObjectUtil.isEmpty(student.getEmail()) ||
                ObjectUtil.isEmpty(student.getGender())){
            return Result.error("Fill in all area!");
        }
        studentService.add(student);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        studentService.deleteById(id);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody Student student){
        studentService.updateById(student);
        return Result.success();

    }

    /**
     * use this pseudo delete normally
     * @param id the data id
     * @return succeed message
     */
    @PutMapping("/pseudo-delete/{id}")
    public Result pseudoDelete(@PathVariable Integer id) {
        studentService.pseudoDeleteById(id);
        return Result.success();
    }

    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam(defaultValue = "1")Integer pageNum,
                             @RequestParam(defaultValue = "5")Integer pageSize,
                             Student student){
        PageInfo<Student> pageInfo = studentService.selectPage(pageNum, pageSize, student);
        return Result.success(pageInfo);

    }
}
