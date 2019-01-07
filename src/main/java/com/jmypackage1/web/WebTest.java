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
        List<User> lists = service.getLists();  //查询全部对象
        map.put("lists",lists); //带数据
        return "index";  //转发
    }
    @RequestMapping("/addUser.do")
    public String addUser(){
        return "addUser";   //转发
    }
    @RequestMapping("/doAddUser.do")
    public String doAddUser(User user){
        service.insert(user);  //添加对象
        //redirect:重定向
        return "redirect:index.do";
    }
    @RequestMapping("/deleteUser.do")
    public String deleteUser(int id){
        service.deleUser(id); //删除对象
        //重定向
        return "redirect:index.do";
    }
    @RequestMapping("/updateUser.do")
    public String updateUser(ModelMap map, int id){
        User user = service.getOne(id); //查询这个对象
        map.put("user", user);  //带数据
        return "updateUser";   //转发
    }
    @RequestMapping("/doUpdateUser.do")
    public String doUpdateUser(User user){
        service.amend(user);  //修改对象
        return "redirect:index.do";
    }
}
