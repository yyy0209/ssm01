package com.jmypackage1.utilTest;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.Map;

public class CookieUtil {
    public static Map<String, Cookie> getCookie(Cookie[] cookies){   //转换成集合
        Map<String,Cookie> maps = new HashMap<>();
        if (cookies!=null){
            for (Cookie coo:cookies
            ) {
                maps.put(coo.getName(),coo);
            }
        }
        return maps;
    }
}
