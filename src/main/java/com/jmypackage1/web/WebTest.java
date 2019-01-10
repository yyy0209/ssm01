package com.jmypackage1.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jmypackage1.pojo.User;
import com.jmypackage1.service.IUserService;
import com.jmypackage1.utilTest.CookieUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class WebTest {
    @Autowired
    private IUserService service;
    @RequestMapping("/register.do")
    public String register(){
        return "register";
    }
    @RequestMapping("/doRegister.do")
    @ResponseBody
    public String doRegister(@RequestParam("unames")String uname,String pwd, String pwds, String tele,HttpServletRequest req, HttpServletResponse resp){
        /*System.out.println(uname);   //测试
        System.out.println(pwd + " " + pwds);  //测试
        System.out.println(tele);  //测试*/
        if (uname!=""){    //判断用户名输入不为空
            User user = service.getOneName(uname);
            //System.out.println(user);
            if (user == null){    //用户名可以使用
                if (pwd!=""&&pwds!=""){   //密码不为空
                    if (pwd.equals(pwds)){  //密码相同
                        return "13";  //不刷新时传出数据
                    }else {    //密码不相等
                        return "14";
                    }
                }else {     //密码为空
                    return "15";
                }
            }else {   //用户名以存在
                return "2";  //不刷新时传出数据
            }
        }else {
            return "0";
        }
    }
    @RequestMapping("/doIt.do")
    @ResponseBody
    public String doIt(@RequestParam("unames")String uname,String pwd, String pwds, String tele,HttpServletRequest req, HttpServletResponse resp){
        /*System.out.println(uname);   //测试
        System.out.println(pwd + " " + pwds);  //测试
        System.out.println(tele);  //测试*/
        User u = new User();
        u.setUsername(uname);
        u.setPassword(pwd);
        u.setTele(tele);
        int result = service.insert(u); //存放到数据库，返回受影响条数
        if (result>0){
            return "1";
        }else {
            return "0";
        }
    }
    @RequestMapping("/list.do")
    public String list(ModelMap map,HttpServletRequest req,User user){
        //第几页
        int pageNum= req.getParameter("pageNum")==null?1:Integer.parseInt(req.getParameter("pageNum"));
        int pageSize=3; //每页数据条数
        PageHelper.startPage(pageNum,pageSize);  //分页信息
        List<User> lists = service.getListsUser(user);  //集合
        String uname;
        if(user.getUsername()==null){
            uname = "";
        }else {
            uname = "&username="+user.getUsername();  //应该判断传入的是否为空
        }
       // String uname = "&username="+user.getUsername();  //应该判断传入的是否为空
        PageInfo<User> page = new PageInfo<>(lists);
        map.addAttribute("uname",uname);
        map.addAttribute("lists",lists);
        map.addAttribute("page",page);
        return "list";
    }
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
    @RequestMapping("/upload.do")
    public String upload(){
        return "upload";
    }
    @RequestMapping("/doUpload.do")
    public String doUpload(@RequestParam("files") MultipartFile[] files){
        for (MultipartFile f:files
             ) {
            if(!f.isEmpty()){
                File file = new File("F:\\测试代码上传图片\\"+f.getOriginalFilename());
                try {
                    FileUtils.copyInputStreamToFile(f.getInputStream(),file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //System.out.println(file.getOriginalFilename());
        return "";
    }
}
