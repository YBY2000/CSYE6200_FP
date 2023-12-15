package com.example.service;

import com.example.entity.UserParticipate;
import com.example.exception.CustomException;
import com.example.mapper.UserParticipateMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserParticipateService {

    @Resource
    UserParticipateMapper userParticipateMapper;

    public void select(UserParticipate userParticipate){
        UserParticipate participated = userParticipateMapper.selectByCondition(userParticipate);       // check if this user have participated in the same exercise
        if (participated != null) {
            throw new CustomException("You already participated in this exercise!");
        }
        userParticipateMapper.participate(userParticipate);
    }

    public PageInfo<UserParticipate> selectPage(Integer pageNum, Integer pageSize, UserParticipate userParticipate) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserParticipate> list = userParticipateMapper.selectAll(userParticipate);
        return PageInfo.of(list);
    }

    public void deleteById(Integer id) {
        userParticipateMapper.deleteById(id);
    }
}
