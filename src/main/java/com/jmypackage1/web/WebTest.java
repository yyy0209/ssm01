package com.jmypackage1.web;

import com.jmypackage1.pojo.User;
import com.jmypackage1.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class WebTest {
    @Autowired
    private IUserService service;
    @RequestMapping("/index.do")
    public String index(ModelMap map){
        List<User> lists = service.getLists();
        map.put("lists",lists);
        return "index";
    }
}
