//package cn.luotuoyulang.springbootspringsecurity.config;
//
//
//import java.util.Collection;
//
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.GrantedAuthority;
///**
// * @Description 自定义令牌对象
// * @Classname JwtAuthenticatioToken
// * @Date 2020/3/15 22:32
// * @Created by liuyuhu
// * @Email 17600520726@163.com
// */
//public class JwtAuthenticatioToken extends UsernamePasswordAuthenticationToken {
//
//    private static final long serialVersionUID = 1L;
//
//    private String token;
//
//    public JwtAuthenticatioToken(Object principal, Object credentials){
//        super(principal, credentials);
//    }
//
//    public JwtAuthenticatioToken(Object principal, Object credentials, String token){
//        super(principal, credentials);
//        this.token = token;
//    }
//
//    public JwtAuthenticatioToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, String token) {
//        super(principal, credentials, authorities);
//        this.token = token;
//    }
//
//    public String getToken() {
//        return token;
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }
//
//    public static long getSerialversionuid() {
//        return serialVersionUID;
//    }
//
//}