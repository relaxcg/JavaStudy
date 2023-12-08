package org.relaxcg.sc.user;

import cn.dev33.satoken.secure.BCrypt;
import lombok.val;
import org.mybatis.spring.annotation.MapperScan;
import org.relaxcg.sc.user.entity.Account;
import org.relaxcg.sc.user.entity.User;
import org.relaxcg.sc.user.mapper.AccountMapper;
import org.relaxcg.sc.user.mapper.UserMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author relaxcg
 * @date 2023/11/30 11:39
 */
@EnableDiscoveryClient
@MapperScan(basePackages = {"org.relaxcg.sc.user.mapper"})
@SpringBootApplication(scanBasePackages = {"org.relaxcg.sc.user", "org.relaxcg.sc.common"})
public class UserMain {
    public static void main(String[] args) {
        SpringApplication.run(UserMain.class, args);
    }
}

@Component
class Runner implements CommandLineRunner {
    @Resource
    private AccountMapper accountMapper;
    @Resource
    private UserMapper userMapper;

    @Override
    public void run(String... args) throws Exception {
        if (accountMapper.selectById(1) != null) {
            return;
        }
        val account = new Account();
        account.setId(1L);
        account.setPhone("10000000000");
        account.setPassword(BCrypt.hashpw("admin123!!"));
        accountMapper.insert(account);
        User user = new User();
        user.setId(1L);
        userMapper.insert(user);
        System.out.println(BCrypt.hashpw("admin123!!"));
    }
}