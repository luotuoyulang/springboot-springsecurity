//package cn.luotuoyulang.springbootspringsecurity.config;
//
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
///**
// * @Description TODO
// * @Classname JwtAuthenticationProvider
// * @Date 2020/3/15 22:22
// * @Created by liuyuhu
// * @Email 17600520726@163.com
// */
//public class JwtAuthenticationProvider extends DaoAuthenticationProvider {
//
//    public JwtAuthenticationProvider(UserDetailsService userDetailsService) {
//        setUserDetailsService(userDetailsService);
//    }
//
//    @Override
//    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication)
//            throws AuthenticationException {
//        if (authentication.getCredentials() == null) {
//            logger.debug("Authentication failed: no credentials provided");
//            throw new BadCredentialsException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
//        }
//
//        String presentedPassword = authentication.getCredentials().toString();
//        String salt = ((JwtUserDetails) userDetails).getSalt();
//        // 覆写密码验证逻辑
//        if (!new PasswordEncoder(salt).matches(userDetails.getPassword(), presentedPassword)) {
//            logger.debug("Authentication failed: password does not match stored value");
//            throw new BadCredentialsException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
//        }
//    }
//
//}
