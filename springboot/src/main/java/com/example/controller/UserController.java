package com.example.controller;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.Result;
import com.example.entity.User;
import com.example.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;


    @PostMapping("/add")
    public Result add(@RequestBody User user) {
        if (ObjectUtil.isEmpty(user.getUsername()) ||
                ObjectUtil.isEmpty(user.getPassword()) ||
                ObjectUtil.isEmpty(user.getLastName()) ||
                ObjectUtil.isEmpty(user.getFirstName()) ||
                ObjectUtil.isEmpty(user.getEmail()) ||
                ObjectUtil.isEmpty(user.getGender())){
            return Result.error("Fill in all area!");
        }
        userService.add(user);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        userService.deleteById(id);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody User user){
        userService.updateById(user);
        return Result.success();

    }

    /**
     * use this pseudo delete normally
     * @param id the data id
     * @return succeed message
     */
    @PutMapping("/pseudo-delete/{id}")
    public Result pseudoDelete(@PathVariable Integer id) {
        userService.pseudoDeleteById(id);
        return Result.success();
    }

    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam(defaultValue = "1")Integer pageNum,
                             @RequestParam(defaultValue = "5")Integer pageSize,
                             User user){
        PageInfo<User> pageInfo = userService.selectPage(pageNum, pageSize, user);
        return Result.success(pageInfo);

    }
}
