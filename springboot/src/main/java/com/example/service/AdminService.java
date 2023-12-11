package com.example.service;

import com.example.entity.Account;
import com.example.exception.CustomException;
import com.example.mapper.AdminMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminService {

    @Resource
    private AdminMapper adminMapper;

    /**
     * handle login service
     */
    public Account login(Account account){
        Account dbAccount = adminMapper.selectByUsername(account.getUsername());
        System.out.println(dbAccount);
        if (dbAccount == null) {
            // user not fount
            throw new CustomException(" Invalid username and/or password. ");
        }
        // check password
        if (!account.getPassword().equals(dbAccount.getPassword())) {
            throw new CustomException(" Invalid username and/or password. ");
        }

        // login successfully
        return dbAccount;
    }
}
