package com.jmypackage1.web;

import com.jmypackage1.utilTest.CookieUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/*拦截全部请求*/
public class TestMethod implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object o) throws Exception {
        Cookie[] cookies = req.getCookies();  //获取所有cookie
        Map<String,Cookie> maps = CookieUtil.getCookie(cookies);  //调用工具类的方法，把CooKie类型转换成集合
        //System.out.println(maps);  //测试
        Cookie coo = maps.get("username");  //找到这个cookie对象
        //System.out.println(coo.getName());
        //System.out.println(coo);
        if (coo == null){
            /*重定向*/
            resp.sendRedirect("logi  n.do");
            /*转发*/
            //req.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(req,resp);
        }else {
            return true;
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
