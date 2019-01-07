package com.jmypackage1.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebTest {
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
