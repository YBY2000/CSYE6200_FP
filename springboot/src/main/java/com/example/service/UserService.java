package com.example.service;

import com.example.common.RoleEnum;
import com.example.entity.Account;
import com.example.entity.User;
import com.example.exception.CustomException;
import com.example.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * handle login service
     */
    public Account login(Account account){
        Account dbUser = userMapper.selectByUsername(account.getUsername());
        System.out.println(dbUser);
        if (dbUser == null) {
            // user not fount
            throw new CustomException(" Invalid username and/or password. ");
        }
        // check password
        if (!account.getPassword().equals(dbUser.getPassword())) {
            throw new CustomException(" Invalid username and/or password. ");
        }

        // login successfully
        return dbUser;
    }

    public void signup(User user) {
        this.add(user);
    }

    public void add(User user){
        User dbUser1 = userMapper.selectByEmail(user.getEmail());
        if (dbUser1 != null) {
            // account (username) already exist
            throw new CustomException("This email has already exists!");
        }
        User dbUser2 = userMapper.selectByUsername(user.getUsername());
        if (dbUser2 != null) {
            // account (username) already exist
            throw new CustomException("Username already exists!");
        }
        user.setAvatar("vue/src/assets/imgs/avatar.png");
        user.setRole(RoleEnum.USER.name());
        userMapper.insert(user);

    }

    public void deleteById(Integer id) {
        userMapper.delete(id);
    }

    public void updateById(User user) {
        User dbUser1 = userMapper.selectByUsername(user.getUsername());
        User dbUser2 = userMapper.selectById(user.getId());
        if (dbUser1 != null && !dbUser2.getUsername().equals(user.getUsername()) ) {
            // account (username) already exist
            throw new CustomException("Username already exists!");
        }
        User dbUser3 = userMapper.selectByEmail(user.getEmail());
        if (dbUser3 != null && !dbUser2.getEmail().equals(user.getEmail()) ) {
            // account (username) already exist
            throw new CustomException("Email already exists!");
        }
        userMapper.updateById(user);
    }

    public PageInfo<User> selectPage(Integer pageNum, Integer pageSize, User user) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userMapper.selectAll(user);

        return PageInfo.of(userList);
    }

    public void pseudoDeleteById(Integer id) {
        userMapper.pseudoDeleteById(id);
    }
}
