package com.example.service;

import com.example.entity.Exercise;
import com.example.mapper.ExerciseMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ExerciseService {

    @Resource
    private ExerciseMapper exerciseMapper;

    /**
     * total: total number of search
     * list: data list
     * @param pageNum current page number
     * @param pageSize size of every page
     * @return the list of data
     */

    public PageInfo<Exercise> selectPage(Integer pageNum, Integer pageSize, String title, String targetMuscle, String difficultyLevel){
        PageHelper.startPage(pageNum, pageSize);
        List<Exercise> exerciseList = exerciseMapper.selectAll(title, targetMuscle, difficultyLevel);
        return PageInfo.of(exerciseList);
    }

    public void add(Exercise exercise) {
        exerciseMapper.insert(exercise);
    }

    public void updateById(Exercise exercise) {
        exerciseMapper.updateById(exercise);
    }

    public void pseudoDeleteById(Integer id) {
        exerciseMapper.pseudoDeleteById(id);
    }

    public void delete(Integer id) {
        exerciseMapper.delete(id);
    }
}
