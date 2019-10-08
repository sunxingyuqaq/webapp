package com.fcsr.manage;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.ClassUtils;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.net.URI;

/**
 * @author Sunxing Yu
 * @date 2019/9/30 15:04
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "file:src/main/webapp/WEB-INF/spring-servlet.xml"})
public class Tests {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void test() {
        Assert.assertNotNull(applicationContext);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            log.info("beanDefinitionName is {}", name);
        }
    }

    @Test
    public void fileUpload() {
        try {
            File file = new File("d:\\试用期修改设计.doc");
            FileInputStream inputStream = new FileInputStream(file);
            MockMultipartFile multipartFile = new MockMultipartFile("file", "试用期修改设计.doc", "application/msword", inputStream);
            Class<?> aClass = ClassUtils.getUserClass(MockMultipartHttpServletRequestBuilder.class);
            Constructor<?> declaredConstructor = aClass.getDeclaredConstructor(URI.class);
            declaredConstructor.setAccessible(true);
            MockMultipartHttpServletRequestBuilder servletRequest = (MockMultipartHttpServletRequestBuilder)
                    declaredConstructor.newInstance(new Object[]{new URI("/test/file")});
            servletRequest.file(multipartFile);
            servletRequest.param("username", "sxy");
            servletRequest.param("moduleName", "test");
            MvcResult mvcResult = mockMvc.perform(servletRequest)
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
            MockHttpServletResponse response = mvcResult.getResponse();
            log.info("响应状态:{},响应内容:{}", response.getStatus(), response.getContentAsString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
