package com.offcn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.offcn.mapper.AddressMapper;
import com.offcn.mapper.UserMapper;
import com.offcn.model.Address;
import com.offcn.model.User;
import com.offcn.model.UserList;
import com.offcn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AddressMapper addressMapper;
    @Override
    public void save(User user) {
        if (user.getId()==null){
            userMapper.insert(user);
            Integer id = user.getId();
            if (user.getAddressList().size()!=0){
                List<Address> addressList = user.getAddressList();
                for (Address address:addressList) {
                    address.setUid(id);
                    addressMapper.insert(address);
                }
            }
        }else {
            userMapper.updateById(user);
            QueryWrapper<Address> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("uid",user.getId());
            addressMapper.delete(queryWrapper);
            List<Address> addressList = user.getAddressList();
            if (addressList!=null){
                for (Address address : addressList) {
                    addressMapper.insert(address);
                }
            }
        }
    }

    @Override
    public void userDelete(Integer id) {
        QueryWrapper<Address> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("uid",id);
        addressMapper.delete(queryWrapper);
        userMapper.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            QueryWrapper<Address> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("uid",user.getId());
            List<Address> addresses = addressMapper.selectList(queryWrapper);
            user.setAddressList(addresses);
        }
        return users;
    }

    @Override
    public User findId(Integer id) {
        User user = userMapper.selectById(id);
        QueryWrapper<Address> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("uid",id);
        List<Address> addresses = addressMapper.selectList(queryWrapper);
        user.setAddressList(addresses);
        return user;
    }

    @Override
    public void addList(UserList userList) {
        List<User> userList1 = userList.getUserList();
        if (userList1.isEmpty()){
            for (User user : userList1) {
                this.save(user);
            }
        }
    }
}
