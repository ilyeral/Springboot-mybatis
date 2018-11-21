package com.example.demo.service;

import com.example.demo.dao.UserMapper;
import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService
{
    // 注入Service依赖
    @Resource
    private UserMapper userMapper;
    /**
     * 添加
     */
    public int addUser(User user){
        return userMapper.insertSelective(user);
    }
    /**
     * 删除
     */
    public int deleteUser(int id){
        return userMapper.deleteByPrimaryKey( id);
    }

    public User login(User user){
        return userMapper.selectByPhoneAndPassword(user);
    }
}
