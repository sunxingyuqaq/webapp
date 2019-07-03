package com.fcsr.manager.dao;

import com.fcsr.manager.entity.User;

import java.util.List;

/**
 * @author sunxingyu
 * @date 2018/3/24
 */
public interface UserMapper {

    List<User> findAll();
}
