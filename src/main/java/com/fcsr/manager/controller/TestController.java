package com.fcsr.manager.controller;

import com.fcsr.manager.entity.User;
import com.fcsr.manager.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author sunxingyu
 * @date 2018/3/24
 */
@RestController
@RequestMapping("/test")
public class TestController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Resource
    private DataSource dataSource;

    @RequestMapping("/all")
    public List<User> findAll() {
        System.out.println(dataSource);
        return this.userService.findAll();
    }

    @PostMapping("/file")
    public String uploadFile(HttpServletRequest request) throws IOException {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        boolean multipart = resolver.isMultipart(request);
        if (multipart) {
            MultipartHttpServletRequest multipartHttpServletRequest = resolver.resolveMultipart(request);
            List<MultipartFile> file = multipartHttpServletRequest.getFiles("file");
            if (file.size() > 0) {
                for (MultipartFile multipartFile : file) {
                    multipartFile.transferTo(new File("d:\\jar\\" + multipartFile.getOriginalFilename()));
                    logger.info("file name is {},o-name is{}", multipartFile.getName(), multipartFile.getOriginalFilename());
                }
            }
        }
        logger.info("url is {}", request.getRequestURL());
        return "ok";
    }
}
