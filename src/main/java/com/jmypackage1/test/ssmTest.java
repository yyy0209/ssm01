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
    private UserDao dao;
    @Test
    public void test(){
        List<User> lists = dao.getLists();
        for (User u:lists
             ) {
            System.out.println(u);
        }
    }
}
