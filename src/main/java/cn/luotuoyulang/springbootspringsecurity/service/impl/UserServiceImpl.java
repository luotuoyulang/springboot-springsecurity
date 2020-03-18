package cn.luotuoyulang.springbootspringsecurity.service.impl;

import cn.luotuoyulang.springbootspringsecurity.entitys.User;
import cn.luotuoyulang.springbootspringsecurity.mapper.UserMapper;
import cn.luotuoyulang.springbootspringsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description TODO
 * @Classname UserServiceImpl
 * @Date 2020/3/17 17:55
 * @Created by liuyuhu
 * @Email 17600520726@163.com
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> findUser() {
        return userMapper.findUser();
    }

    @Override
    public User getUserByName(String username) {
        return userMapper.getUserByName(username);
    }
}
