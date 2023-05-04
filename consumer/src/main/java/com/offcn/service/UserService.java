package com.offcn.service;

import com.offcn.model.User;
import com.offcn.model.UserList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Component
@FeignClient(value = "user-provider")
public interface UserService {
    @RequestMapping("save")
    void save(User user);
    @DeleteMapping("/{id}")
    void userDelete(Integer id);
    @GetMapping("/findAll")
    List<User> findAll();
    @GetMapping("/{id}")
    User findId(Integer id);
    @RequestMapping("list")
    void addList(UserList userList);
}
