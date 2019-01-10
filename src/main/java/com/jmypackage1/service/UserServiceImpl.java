package com.jmypackage1.service;   /*这个包是逻辑处理*/

import com.jmypackage1.dao.UserDao;
import com.jmypackage1.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserDao dao;
    @Override
    public int insert(User user) {   //去调用了一下dao层
        return dao.insert(user);
    }

    @Override
    public User getOneName(String username) {  //通过用户名查找出这个用户
        return dao.getOneName(username);   //去dao层，通过用户名查找出这个用户
    }

    @Override
    public int amend(User user) {  //修改
        return dao.amend(user);
    }

    @Override
    public List<User> getLists() {
        return dao.getLists();
    }

    @Override
    public List<User> getListsUser(User user) {
        return dao.getListsUser(user);
    }

    @Override
    public int deleUser(int id) {
        return dao.deleUser(id);
    }

    @Override
    public User getOne(int id) {
        return dao.getOne(id);
    }
}
