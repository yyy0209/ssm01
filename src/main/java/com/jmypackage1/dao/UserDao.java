package com.jmypackage1.dao;

import com.jmypackage1.pojo.User;

import java.util.List;

public interface UserDao {
    public int insert(User user);   //插入数据的方法
    public List<User> getLists();   //查询
    public User getOneName(String username);   //通过用户名查找出这个用户
    public int amend(User user);   //修改用户
    public int deleUser(int id);  //删除用户
    public User getOne(int id);  //查找一个
}
