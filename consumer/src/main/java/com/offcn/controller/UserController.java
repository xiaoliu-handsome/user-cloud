package com.offcn.controller;

import com.offcn.model.User;
import com.offcn.model.UserList;
import com.offcn.service.UserService;
import com.offcn.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("save")
    public Result save(@RequestBody User user){
        userService.save(user);
        return new Result(true,"成功",1);
    }
    @DeleteMapping("{id}")
    public Result delete(@PathVariable Integer id){
        userService.userDelete(id);
        return new Result(true,"成功",1);
    }
    @GetMapping("/findAll")
    public Result findAll(){
        List<User> all = userService.findAll();
        return new Result(true,"成功",all);
    }
    @GetMapping("/{id}")
    public Result findId(@PathVariable Integer id){
        User id1 = userService.findId(id);
        return new Result(true,"成功",id1);
    }
    @RequestMapping("list")
    public Result addList(@RequestBody UserList userList){
        userService.addList(userList);
        return new Result();
    }
}
