package com.example.mapper;

import com.example.entity.UserParticipate;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserParticipateMapper {

    @Insert("insert into user_participate (title, exercise_id, user_id) values " +
            "(#{title}, #{exercise_id}, #{user_id})")
    void participate(UserParticipate userParticipate);

    @Select("select * from user_participate where user_id=#{user_id} and exercise_id=#{exercise_id}")
    UserParticipate selectByCondition(UserParticipate userParticipate);

    @Select("select * from user_participate where user_id like #{user_id}")
    List<UserParticipate> selectAll(UserParticipate userParticipate);

    @Delete("delete from user_participate where id=#{id}")
    void deleteById(Integer id);
}
