package com.fcsr.manager.controller;

import com.fcsr.manager.entity.User;
import com.fcsr.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

/**
 * @author sunxingyu
 * @date 2018/3/24
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserService userService;

    @Resource
    private DataSource dataSource;

    @RequestMapping("/all")
    public List<User> findAll(){
        System.out.println(dataSource);
        return this.userService.findAll();
    }
}
