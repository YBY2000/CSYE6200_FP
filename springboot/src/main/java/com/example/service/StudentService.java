package com.example.service;

import com.example.entity.Account;
import com.example.exception.CustomException;
import com.example.mapper.AdminMapper;
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
}
