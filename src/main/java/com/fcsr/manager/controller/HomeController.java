package com.fcsr.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

/**
 * @author sunxingyu
 * @date 2018/4/23
 */
@Controller
@RequestMapping
public class HomeController {

    @GetMapping("/welcome")
    public String welcome(Model model){
        model.addAttribute("hello","hello world");
        model.addAttribute("users", Arrays.asList(1,2,3,4,5));
        return "views/welcome";
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("hello","hello world");
        return "views/index";
    }
}
