package com.example.controller;

import com.example.common.Result;
import com.example.entity.UserParticipate;
import com.example.service.UserParticipateService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.awt.*;

@RestController
@RequestMapping("/userParticipate")
public class UserParticipateController {

    @Resource
    UserParticipateService userParticipateService;

    @PostMapping("/select")
    public Result select(@RequestBody UserParticipate userParticipate) {
        userParticipateService.select(userParticipate);
        return Result.success();
    }


    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize,
                             UserParticipate userParticipate) {
        PageInfo<UserParticipate> pageInfo = userParticipateService.selectPage(pageNum, pageSize, userParticipate);
        return Result.success(pageInfo);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        userParticipateService.deleteById(id);
        return Result.success();
    }
}
