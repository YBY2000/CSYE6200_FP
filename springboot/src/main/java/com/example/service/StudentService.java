package com.example.service;

import com.example.common.RoleEnum;
import com.example.entity.Account;
import com.example.entity.Student;
import com.example.exception.CustomException;
import com.example.mapper.StudentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
}
