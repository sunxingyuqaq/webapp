package com.fcsr.manager.service;

import com.fcsr.manager.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author sunxingyu
 * @date 2018/3/24
 */
@Mapper
public interface UserService {

    List<User> findAll();
}
