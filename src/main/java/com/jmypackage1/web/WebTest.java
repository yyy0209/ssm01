package com.jmypackage1.web;

import com.jmypackage1.pojo.User;
import com.jmypackage1.service.IUserService;
import com.jmypackage1.utilTest.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

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
    @RequestMapping("/login.do")
    public String login(){
        return "login";
    }
    @RequestMapping("/doLogin.do")
    public String doLogin(@RequestParam("username") String uname, @RequestParam("pwd") String password, HttpServletRequest req, HttpServletResponse resp){
        User user = service.getOneName(uname);  //通过用户名查找这个用户
        //System.out.println(user);
        if(user!=null){
            if (user.getPassword().equals(password)) {
                Cookie coo = new Cookie("username",uname);  //创建cookie
                Cookie coop = new Cookie("password",password);
                coo.setMaxAge(60*60*24*7);   //设置cooKie存储时间
                coop.setMaxAge(60*60*24*7);
                resp.addCookie(coo);   //添加到浏览器
                resp.addCookie(coop);
                //System.out.println(coo.getName()+ "  "+ coo.getValue());  //测试
                //System.out.println(coop.getName()+ "  "+ coop.getValue());  //测试
                HttpSession session = req.getSession();   //获取session
                session.setAttribute("user",user);   //给服务器session添加内容
                return "redirect:index.do";
            }else {
                //System.out.println("密码错误");
                return "redirect:login.do";
            }
        }else {
            //System.out.println("没有找到用户");
            return "redirect:login.do";
        }
    }
    @RequestMapping("/exit.do")
    public String exit(HttpServletRequest req, HttpServletResponse resp){
        Cookie[] cookies =req.getCookies();
        Map<String,Cookie> maps = CookieUtil.getCookie(cookies);
        Cookie c = maps.get("username");  //获取cookie信息
        c.setValue("");
        //Cookie coo = new Cookie("username","");
        c.setMaxAge(0);
        resp.addCookie(c);
        HttpSession session = req.getSession();
        session.invalidate();//消除会话
        return "redirect:login.do";
    }
}
