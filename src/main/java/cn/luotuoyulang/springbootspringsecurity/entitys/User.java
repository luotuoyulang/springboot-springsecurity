package cn.luotuoyulang.springbootspringsecurity.entitys;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @Description TODO
 * @Classname User
 * @Date 2020/3/16 22:48
 * @Created by liuyuhu
 * @Email 17600520726@163.com
 */
@Data
public class User {

    private Integer id;
    private String username;
    private String realname;
    private String password;
    private Date createdate;
    private Date lastlogintime;
    private Boolean enabled;
    private Boolean accountnonexpired;
    private Boolean credentialsnonexpired;
    private Boolean accountnonlocked;
    private List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//    private String role;

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorityList;
//    }
//
//    @Override
//    public String getPassword() {
//        return this.password;
//    }
//
//    @Override
//    public String getUsername() {
//        return this.username;
//    }
//
//    //账户是否未过期,过期无法验证
//    @Override
//    public boolean isAccountNonExpired() {
//        return this.accountnonexpired;
//    }
//
//    //指定用户是否解锁,锁定的用户无法进行身份验证
//    @Override
//    public boolean isAccountNonLocked() {
//        return this.isAccountNonLocked();
//    }
//
//    //指示是否已过期的用户的凭据(密码),过期的凭据防止认证
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return this.credentialsnonexpired;
//    }
//
//    //是否可用 ,禁用的用户不能身份验证
//    @Override
//    public boolean isEnabled() {
//        return this.enabled;
//    }
}
