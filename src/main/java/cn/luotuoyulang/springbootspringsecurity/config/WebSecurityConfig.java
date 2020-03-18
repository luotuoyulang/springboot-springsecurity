package cn.luotuoyulang.springbootspringsecurity.config;

import cn.luotuoyulang.springbootspringsecurity.entitys.Permission;
import cn.luotuoyulang.springbootspringsecurity.mapper.PermissionMapper;
import cn.luotuoyulang.springbootspringsecurity.security.UserDetailsServiceImpl;
import cn.luotuoyulang.springbootspringsecurity.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;


/**
 * @Description Security 核心配置
 * @Classname WebSecurityConfig
 * @Date 2020/3/15 22:20
 * @Created by liuyuhu
 * @Email 17600520726@163.com
 */
@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)//激活方法上的PreAuthorize注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    MyAuthenticationFailureHandler failureHandler;
//
//    @Autowired
//    MyAuthenticationSuccessHandler successHandler;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * @Description 配置认证用户信息和权限
     * @param auth
     * @return {@link }
     * @throws
     * @author liuyuhu
     * @date 2020/3/15 22:46
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 使用自定义身份验证组件
//        auth.authenticationProvider(new JwtAuthenticationProvider(userDetailsService));
        // admin 账号
//        auth.inMemoryAuthentication().
//                withUser("admin").password("1")
//                .authorities("addOrder");
//        // adminAdd 账号
//        auth.inMemoryAuthentication().
//                withUser("adminAdd").password("1")
//                .authorities("showOrder");
        // 如果想实现动态账号与数据库关联在该地方改为查询数据库
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(new PasswordEncoder() {
            // 对表单密码进行加密
            @Override
            public String encode(CharSequence rawPassword) {
                return MD5Utils.MD5Upper((String)rawPassword);
            }

            // 加密的密码与数据库密码进行比对  encodedPassword 数据库加密字段 rawPassword 是表单未加密字段
            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                System.out.println("MD5Utils.MD5Upper((String)rawPassword)=="+MD5Utils.MD5Upper((String)rawPassword)+"==encodedPassword=="+ encodedPassword);
                return MD5Utils.MD5Upper((String)rawPassword).equals(encodedPassword);
            }
        });
    }

//    @Bean
//    public UserDetailsService userDetailsService() {    //用户登录实现
//        return new UserDetailsService() {
//            @Autowired
//            private UserMapper userMapper;
//
//            @Override
//            public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//                User user = userMapper.getUserByName(s);
//                if (user == null) throw new UsernameNotFoundException("Username " + s + " not found");
//                return new SecurityUser(user);
//            }
//        };
//    }

    /**
     * @Description 配置拦截请求资源
     *              选择 httpBasic 模式
     * @param http
     * @return {@link }
     * @throws
     * @author liuyuhu
     * @date 2020/3/15 22:47
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 如何权限控制 给每一个请求路径分配一个权限名称 让后账号只要管来奶该名称，就可以访问权限
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests
                = http.authorizeRequests();
        List<Permission> permissionList = permissionMapper.findPermission();
        for (Permission permission : permissionList) {
            authorizeRequests.antMatchers(permission.getUrl()).hasAnyAuthority(permission.getPermtag());
        }

//        http.authorizeRequests()
//                .antMatchers("/addOrder").hasAnyAuthority("addOrder")
//                .antMatchers("/showOrder").hasAnyAuthority("showOrder")
//                .antMatchers("/updateOrder").hasAnyAuthority("updateOrder")
//                .antMatchers("/deleteOrder").hasAnyAuthority("deleteOrder")
//                // 放行登录请求
        authorizeRequests.antMatchers("/login").permitAll()
                .antMatchers("/**").fullyAuthenticated().and().formLogin();
                 // 可以配置登录页面 可以禁止传token
//                .loginPage("").and().csrf().disable();
//                .httpBasic();


        // 禁用 csrf, 由于使用的是JWT，我们这里不需要csrf
//        http.cors().and().csrf().disable()
//                .authorizeRequests()
//                // 跨域预检请求
//                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                // web jars
//                .antMatchers("/webjars/**").permitAll()
//                // 查看SQL监控（druid）
//                .antMatchers("/druid/**").permitAll()
//                // 首页和登录页面
//                .antMatchers("/").permitAll()
//                .antMatchers("/login").permitAll()
//                // swagger
//                .antMatchers("/swagger-ui.html").permitAll()
//                .antMatchers("/swagger-resources").permitAll()
//                .antMatchers("/v2/api-docs").permitAll()
//                .antMatchers("/webjars/springfox-swagger-ui/**").permitAll()
//                // 验证码
//                .antMatchers("/captcha.jpg**").permitAll()
//                // 服务监控
//                .antMatchers("/actuator/**").permitAll()
//                // 其他所有请求需要身份认证
//                .anyRequest().authenticated();
//        // 退出登录处理器
//        http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
//        // token验证过滤器
//        http.addFilterBefore(new JwtAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);
    }

//    @Bean
//    @Override
//    public AuthenticationManager authenticationManager() throws Exception {
//        return super.authenticationManager();
//    }

    /**
     * @Description 否则报错,密码强制不用加密
     * @param
     * @return {@link {@link NoOpPasswordEncoder}}
     * @throws
     * @author liuyuhu
     * @date 2020/3/15 23:15
     */
//    @Bean
//    public static NoOpPasswordEncoder passwordEncoder(){
//        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
//    }

}
