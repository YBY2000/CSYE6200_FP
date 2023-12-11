package com.example.service;

import com.example.entity.Admin;
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
    public Admin login(Admin admin){
        Admin dbAdmin = adminMapper.selectByUsername(admin.getUsername());
        System.out.println(dbAdmin);
        if (dbAdmin == null) {
            // user not fount
//            throw new CustomException(" Invalid username and/or password. ");
            throw new CustomException(" no user ");
        }
        // check password
        if (!admin.getPassword().equals(dbAdmin.getPassword())) {
//            throw new CustomException(" Invalid username and/or password. ");
            throw new CustomException(" Wrong password ");
        }

        // login successfully
        return dbAdmin;
    }
}
