package cn.luotuoyulang.springbootspringsecurity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@MapperScan("cn.luotuoyulang.springbootspringsecurity.mapper")
@SpringBootApplication
public class SpringbootSpringsecurityApplication {

    /**
     * @Description
     *  Security 俩种模式 fromLogin 表单提交认证模式
     *  httpBasic 浏览器与服务器做认证授权
     * @param args
     * @return {@link }
     * @throws
     * @author liuyuhu
     * @date 2020/3/15 21:36
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringbootSpringsecurityApplication.class, args);
    }

}
