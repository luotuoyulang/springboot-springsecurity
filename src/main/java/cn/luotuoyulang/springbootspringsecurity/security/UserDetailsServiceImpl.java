package cn.luotuoyulang.springbootspringsecurity.security;

import cn.luotuoyulang.springbootspringsecurity.entitys.Permission;
import cn.luotuoyulang.springbootspringsecurity.entitys.Role;
import cn.luotuoyulang.springbootspringsecurity.entitys.User;
import cn.luotuoyulang.springbootspringsecurity.mapper.PermissionMapper;
import cn.luotuoyulang.springbootspringsecurity.mapper.RoleMapper;
import cn.luotuoyulang.springbootspringsecurity.mapper.UserMapper;
import cn.luotuoyulang.springbootspringsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description TODO
 * @Classname UserDetailsServiceImpl
 * @Date 2020/3/15 22:30
 * @Created by liuyuhu
 * @Email 17600520726@163.com
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1、根据用户名称查询数据用户信息
        // 1、底层会根据数据库查询用户信息，判断密码是否正确

        User user = userMapper.getUserByName(username);
        List<Permission> permissionList = permissionMapper.findPermissionByUsername(username);
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        if(permissionList != null && permissionList.size() > 0){
            for (Permission permission : permissionList) {
                authorities.add(new SimpleGrantedAuthority(permission.getPermtag()));
            }
        }
        List<Role> roleList = roleMapper.findRoleByUsername(username);
        authorities.add(new SimpleGrantedAuthority("ROLE_A"));
        user.setAuthorities(authorities);
        return new SecurityUser(user);
//        //动态授权
//        Set<Role> roles = user.getRoles();
//        for (Role role : roles) {
//            list.add(new SimpleGrantedAuthority(role.getKeyword()));//授予角色
//            Set<Permission> permissions = role.getPermissions();
//            for (Permission permission : permissions) {
//                String keyword = permission.getKeyword();//权限关键字（权限标识）
//                list.add(new SimpleGrantedAuthority(keyword));
//            }
//        }
//
//        return new org.springframework.security.core.userdetails.User(username,user.getPassword(),list);


//        return null;
//        if (user == null) {
//            throw new UsernameNotFoundException("该用户不存在");
//        }
//        // 用户权限列表，根据用户拥有的权限标识与如 @PreAuthorize("hasAuthority('sys:menu:view')") 标注的接口对比，决定是否可以调用接口
//        Set<String> permissions = sysUserService.findPermissions(user.getName());
//        List<GrantedAuthority> grantedAuthorities = permissions.stream().map(GrantedAuthorityImpl::new).collect(Collectors.toList());
//        return new JwtUserDetails(user.getName(), user.getPassword(), user.getSalt(), grantedAuthorities);
    }
}
