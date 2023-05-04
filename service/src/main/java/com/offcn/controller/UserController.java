package com.offcn.controller;



import com.offcn.model.User;
import com.offcn.model.UserList;
import com.offcn.service.UserService;
import com.offcn.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController

public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("save")
    public void save(@RequestBody User user){
        userService.save(user);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        userService.userDelete(id);
    }
    @GetMapping("/findAll")
    public List<User> findAll(){
        List<User> all = userService.findAll();
        return all;
    }
    @GetMapping("/{id}")
    public User findId(@PathVariable Integer id){
        User id1 = userService.findId(id);
        return id1;
    }
    @RequestMapping("list")
    public void addList(@RequestBody UserList userList){
        userService.addList(userList);
    }
}
