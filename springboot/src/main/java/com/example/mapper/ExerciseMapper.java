package com.example.mapper;

import com.example.entity.Exercise;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ExerciseMapper {

    @Select("select * from exercise where title like concat('%',#{title}, '%') " +
            "and targetMuscle like concat('%',#{targetMuscle}, '%') " +
            "and difficultyLevel like concat('%',#{difficultyLevel}, '%') and status=1 order by id asc")
    List<Exercise> selectAll(String title, String targetMuscle, String difficultyLevel);

    @Insert("insert into exercise (title, targetMuscle, equipment, description, difficultyLevel, repetitionRange)" +
            "values (#{title}, #{targetMuscle}, #{equipment}, #{description}, #{difficultyLevel}, #{repetitionRange})")
    void insert(Exercise exercise);

    @Update("update exercise set title=#{title}, targetMuscle=#{targetMuscle}, " +
            "equipment=#{equipment}, description=#{description}, difficultyLevel=#{difficultyLevel}," +
            " repetitionRange=#{repetitionRange} where id = #{id}")
    void updateById(Exercise exercise);

    @Update("update exercise set status=0 where id = #{id}")
    void pseudoDeleteById(Integer id);

    @Delete("delete from exercise where id = #{id}")
    void delete(Integer id);
}
