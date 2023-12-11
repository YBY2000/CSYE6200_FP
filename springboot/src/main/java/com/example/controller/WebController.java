package com.example.controller;

import com.example.common.Result;
import com.example.common.RoleEnum;
import com.example.entity.Account;
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
        System.out.println(account.getRole());
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

}
