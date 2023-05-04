package com.offcn.service;

import com.offcn.model.User;
import com.offcn.model.UserList;

import java.util.List;

public interface UserService {
    void save(User user);
    void userDelete(Integer id);
    List<User> findAll();
    User findId(Integer id);
    void addList(UserList userList);
}
