package com.example.controller;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.Result;
import com.example.common.RoleEnum;
import com.example.entity.Account;
import com.example.entity.User;
import com.example.service.AdminService;
import com.example.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class WebController {

    @Resource
    private AdminService adminService;

    @Resource
    private UserService userService;
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
        } else if (RoleEnum.USER.name().equals(account.getRole())) {
            // User Login
            dbAccount = userService.login(account);
        } else {
            return Result.error("Invalid Role!");
        }
        return Result.success(dbAccount);
    }

    /**
     * Login request API
     */
    @PostMapping("/signup")
    public Result signup(@RequestBody User user) {
        if (ObjectUtil.isEmpty(user.getUsername()) ||
                ObjectUtil.isEmpty(user.getPassword()) ||
                ObjectUtil.isEmpty(user.getLastName()) ||
                ObjectUtil.isEmpty(user.getFirstName()) ||
                ObjectUtil.isEmpty(user.getEmail()) ||
                ObjectUtil.isEmpty(user.getGender())){
            return Result.error("Fill in all area!");
        }
        userService.signup(user);
;        return Result.success();
    }

}
