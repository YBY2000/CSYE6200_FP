package com.example.service;

import com.example.common.RoleEnum;
import com.example.entity.Account;
import com.example.entity.Student;
import com.example.exception.CustomException;
import com.example.mapper.StudentMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentService {

    @Resource
    private StudentMapper studentMapper;

    /**
     * handle login service
     */
    public Account login(Account account){
        Account dbStudent = studentMapper.selectByUsername(account.getUsername());
        System.out.println(dbStudent);
        if (dbStudent == null) {
            // user not fount
            throw new CustomException(" Invalid username and/or password. ");
        }
        // check password
        if (!account.getPassword().equals(dbStudent.getPassword())) {
            throw new CustomException(" Invalid username and/or password. ");
        }

        // login successfully
        return dbStudent;
    }

    public void signup(Student student) {
        this.add(student);
    }

    public void add(Student student){
        Student dbStudent1 = studentMapper.selectByEmail(student.getEmail());
        if (dbStudent1 != null) {
            // account (username) already exist
            throw new CustomException("This email has already exists!");
        }
        Student dbStudent2 = studentMapper.selectByUsername(student.getUsername());
        if (dbStudent2 != null) {
            // account (username) already exist
            throw new CustomException("Username already exists!");
        }
        student.setAvatar("vue/src/assets/imgs/avatar.png");
        student.setRole(RoleEnum.STUDENT.name());
        studentMapper.insert(student);

    }

    public void deleteById(Integer id) {
        studentMapper.delete(id);
    }

    public void updateById(Student student) {
        Student dbStudent1 = studentMapper.selectByUsername(student.getUsername());
        Student dbStudent2 = studentMapper.selectById(student.getId());
        if (dbStudent1 != null && !dbStudent2.getUsername().equals(student.getUsername()) ) {
            // account (username) already exist
            throw new CustomException("Username already exists!");
        }
        Student dbStudent3 = studentMapper.selectByEmail(student.getEmail());
        if (dbStudent3 != null && !dbStudent2.getEmail().equals(student.getEmail()) ) {
            // account (username) already exist
            throw new CustomException("Email already exists!");
        }
        studentMapper.updateById(student);
    }

    public PageInfo<Student> selectPage(Integer pageNum, Integer pageSize, Student student) {
        PageHelper.startPage(pageNum, pageSize);
        List<Student> studentList = studentMapper.selectAll(student);

        return PageInfo.of(studentList);
    }

    public void pseudoDeleteById(Integer id) {
        studentMapper.pseudoDeleteById(id);
    }
}
