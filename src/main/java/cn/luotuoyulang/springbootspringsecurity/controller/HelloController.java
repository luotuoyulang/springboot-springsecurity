package cn.luotuoyulang.springbootspringsecurity.controller;

import cn.luotuoyulang.springbootspringsecurity.entitys.User;
import cn.luotuoyulang.springbootspringsecurity.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description TODO
 * @Classname HelloController
 * @Date 2020/3/15 23:18
 * @Created by liuyuhu
 * @Email 17600520726@163.com
 */
@RestController
public class HelloController {

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/hello")
    public String hello(){
        return "Hello World ！！！";
    }

    @GetMapping("/addOrder")
    public String addOrder(){
        return "addOrder ！！！";
    }

    @PreAuthorize("hasRole('ROLE_A')")//表示用户必须拥有ROLE_ADMIN角色才能调用当前方法
//    @PreAuthorize("hasRole('showOrder')")
//    @PreAuthorize("hasAuthority('updateOrder')")//权限校验
//    @PreAuthorize("hasAuthority('showOrder')")//权限校验
    @GetMapping("/showOrder")
    public String showOder(){
        return "showOder ！！！";
    }

    @GetMapping("/updateOrder")
    public String updateOrder(){
        return "updateOrder ！！！";
    }

    @GetMapping("/deleteOrder")
    public String deleteOrder(){
        return "deleteOrder ！！！";
    }

    @GetMapping("/change")
    public String change(){
        userDetailsServiceImpl.loadUserByUsername("a");
        return "change ！！！";
    }
}
