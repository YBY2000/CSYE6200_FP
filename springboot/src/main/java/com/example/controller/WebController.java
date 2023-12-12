package com.example.controller;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.Result;
import com.example.common.RoleEnum;
import com.example.entity.Account;
import com.example.entity.Student;
import com.example.service.AdminService;
import com.example.service.StudentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class WebController {

    @Resource
    private AdminService adminService;

    @Resource
    private StudentService studentService;
    /**
     * Default request API
     */
    @GetMapping("/")
    public Result hello() {
        return Result.success();
    }

    /**
     * Login request API
     */
    @PostMapping("/login")
    public Result login(@RequestBody Account account) {
        Account dbAccount;
        if (RoleEnum.ADMIN.name().equals(account.getRole())){
            // Admin Login
            dbAccount = adminService.login(account);
        } else if (RoleEnum.STUDENT.name().equals(account.getRole())) {
            // Student Login
            dbAccount = studentService.login(account);
        } else {
            return Result.error("Invalid Role!");
        }
        return Result.success(dbAccount);
    }

    /**
     * Login request API
     */
    @PostMapping("/signup")
    public Result signup(@RequestBody Student student) {
        if (ObjectUtil.isEmpty(student.getUsername()) ||
                ObjectUtil.isEmpty(student.getPassword()) ||
                ObjectUtil.isEmpty(student.getLastName()) ||
                ObjectUtil.isEmpty(student.getFirstName()) ||
                ObjectUtil.isEmpty(student.getEmail()) ||
                ObjectUtil.isEmpty(student.getGender())){
            return Result.error("Fill in all area!");
        }
        studentService.signup(student);
;        return Result.success();
    }

}
