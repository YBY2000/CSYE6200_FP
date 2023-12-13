package com.example.controller;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.Result;
import com.example.entity.Exercise;
import com.example.service.ExerciseService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/exercise")
public class ExerciseController {

    @Resource
    private ExerciseService exerciseService;

    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize,
                             @RequestParam String title,
                             @RequestParam String targetMuscle,
                             @RequestParam String difficultyLevel) {
        PageInfo<Exercise> exercisePageInfo = exerciseService.selectPage(pageNum, pageSize, title, targetMuscle, difficultyLevel);
        return Result.success(exercisePageInfo);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Exercise exercise) {
        if (ObjectUtil.isEmpty(exercise.getTitle()) ||
                ObjectUtil.isEmpty(exercise.getTargetMuscle()) ||
                ObjectUtil.isEmpty(exercise.getDescription()) ||
                ObjectUtil.isEmpty(exercise.getDifficultyLevel()) ||
                ObjectUtil.isEmpty(exercise.getRepetitionRange())){
            return Result.error("Fill in all area!");
        }
        exerciseService.add(exercise);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody Exercise exercise) {
        exerciseService.updateById(exercise);
        return Result.success();
    }

    /**
     * use this pseudo delete normally
     * @param id the data id
     * @return succeed message
     */
    @PutMapping("/pseudo-delete/{id}")
    public Result pseudoDelete(@PathVariable Integer id) {
        exerciseService.pseudoDeleteById(id);
        return Result.success();
    }

    /**
     * permanently delete data, use it CAREFULLY!
     * @param id the data id
     * @return succeed message
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        exerciseService.delete(id);
        return Result.success();
    }

}
