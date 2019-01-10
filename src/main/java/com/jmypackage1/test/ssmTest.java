package com.jmypackage1.test;

import com.jmypackage1.dao.UserDao;
import com.jmypackage1.pojo.User;
import com.jmypackage1.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ssmTest {
    @Resource
    //private UserDao dao;
    private IUserService service;
    @Test
    public void test01(){  /*查询全部*/
        List<User> lists = service.getLists();  //查询单元测试
        for (User u:lists
             ) {
            System.out.println(u);
        }
    }
    @Resource
    private UserDao dao;
    @Test
    public void test03(){  /*查询全部*/
        List<User> lists = dao.getLists();  //查询单元测试
        for (User u:lists
        ) {
            System.out.println(u);
        }
    }
    @Test
    public void test02(){  /*添加对象*/
        User user = new User();
        user.setUsername("epop");
        user.setPassword("123");
        user.setTele("88888833");
        int i = service.insert(user);
        System.out.println(i);
    }
}
