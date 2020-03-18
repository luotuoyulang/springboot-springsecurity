package cn.luotuoyulang.springbootspringsecurity.controller;

import cn.luotuoyulang.springbootspringsecurity.entitys.User;
import cn.luotuoyulang.springbootspringsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description TODO
 * @Classname UserController
 * @Date 2020/3/17 17:51
 * @Created by liuyuhu
 * @Email 17600520726@163.com
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PreAuthorize("hasRole('getUser')")
    @GetMapping("/getUser")
    public List<User> findUser(){
        List<User> userList = userService.findUser();
        return userList;
    }


}
