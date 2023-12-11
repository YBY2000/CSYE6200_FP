package com.example.controller;

import com.example.common.Result;
import com.example.entity.Account;
import com.example.service.AdminService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class WebController {

    @Resource
    private AdminService adminService;
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
        Account dbAccount = adminService.login(account);
        return Result.success(dbAccount);
    }

}
