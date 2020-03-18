package cn.luotuoyulang.springbootspringsecurity.service;

import cn.luotuoyulang.springbootspringsecurity.entitys.User;

import java.util.List;

/**
 * @Description TODO
 * @Classname UserService
 * @Date 2020/3/17 17:54
 * @Created by liuyuhu
 * @Email 17600520726@163.com
 */
public interface UserService {

    List<User> findUser();

    User getUserByName(String username);
}
