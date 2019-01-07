package com.jmypackage1.pojo;  /*这个包里面放表*/

import lombok.Data;

@Data
public class User {   //一类一表
    private Integer id;   //id
    private String username;   //用户名
    private String password;   //密码
    private String tele;   //电话号码
    private Integer grade_id;  //等级id
}
