package com.fcsr.manager.controller;

import com.fcsr.manager.entity.User;
import com.fcsr.manager.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author sunxingyu
 * @date 2018/3/24
 */
@RestController
@RequestMapping("/test")
public class TestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Resource
    private DataSource dataSource;

    @RequestMapping("/all")
    public List<User> findAll() {
        System.out.println(dataSource);
        return this.userService.findAll();
    }

    @PostMapping(value = "/file",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(HttpServletRequest request, String username, String moduleName) throws IOException {

        Assert.notNull(username,"not be null");

        Assert.notNull(moduleName,"not be null");
        // 创建list集合，用于接收上传文件的路径
        List<String> filePathList = new ArrayList<>();

        // 拼接文件上传位置，这里使用Tomcat服务器，将文件上传到webapps中，和项目同目录，files将用于保存上传的文件，将上传的文件于项目分开
        String strPath = "d:\\jar,files," + moduleName + "," + username;

        // 解析出文件存放路径位置
        String filepath = strPath.replace(',', File.separatorChar);

        log.debug("文件上传路劲位置-------->>>>>>>>>>>>" + filepath);

        // 转换request，解析出request中的文件
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        // 获取文件map集合
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();

        String fileName;

        // 循环遍历，取出单个文件
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {

            // 获取单个文件
            MultipartFile mf = entity.getValue();

            // 获得原始文件名
            fileName = mf.getOriginalFilename();

            // 截取文件类型; 这里可以根据文件类型进行判断
            String fileType = fileName.substring(fileName.lastIndexOf('.'));

            try {
                // 截取上传的文件名称
                String newFileName = fileName.substring(0, fileName.lastIndexOf('.'));

                log.debug("上传来的文件名称------->>>>>>>>>" + newFileName);

                // 拼接上传文件位置
                String newFilePath = filepath + File.separatorChar + newFileName + fileType;

                log.debug("拼接好的文件路径地址------------->>>>>>>>" + newFilePath);

                // 重新组装文件路径，用于保存在list集合中
                String filepathUrl = "files" + File.separatorChar + moduleName + File.separatorChar + username
                        + File.separatorChar + newFileName + fileType;

                log.debug("文件位置---------------->>>>>>>>>>" + filepathUrl);

                // 创建文件存放路径实例
                File dest = new File(filepath);

                // 判断文件夹不存在就创建
                if (!dest.exists()) {
                    dest.mkdirs();
                }

                // 创建文件实例
                File uploadFile = new File(newFilePath);

                // 判断文件已经存在，则删除该文件
                if (uploadFile.exists()) {
                    uploadFile.delete();
                }

                log.debug("start upload file-------------->>>>>>> " + fileName);

                // 利于spring中的FileCopyUtils.copy()将文件复制
                FileCopyUtils.copy(mf.getBytes(), uploadFile);

                // 将文件路径存入list集合中
                filePathList.add(filepathUrl);

            } catch (IOException e) {
                e.printStackTrace();
                log.error("upload failed. filename: " + fileName + "---->>>error message ----->>>>> " + e.getMessage());
                return null;
            }
        }
        return String.join(",", filePathList);
    }
}
