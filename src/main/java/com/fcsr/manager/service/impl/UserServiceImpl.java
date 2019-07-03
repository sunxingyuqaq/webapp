package com.fcsr.manager.service.impl;

import com.fcsr.manager.dao.UserMapper;
import com.fcsr.manager.entity.User;
import com.fcsr.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sunxingyu
 * @date 2018/3/24
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> findAll() {
        return userMapper.findAll();
    }
}
