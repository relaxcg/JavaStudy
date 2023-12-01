package org.relaxcg.sc.user;

import cn.dev33.satoken.secure.BCrypt;
import lombok.val;
import org.mybatis.spring.annotation.MapperScan;
import org.relaxcg.sc.common.web.Result;
import org.relaxcg.sc.user.entity.Account;
import org.relaxcg.sc.user.mapper.AccountMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author relaxcg
 * @date 2023/11/30 11:39
 */
@RestController
@MapperScan(basePackages = {"org.relaxcg.sc.user.mapper"})
@SpringBootApplication(scanBasePackages = {"org.relaxcg.sc.user", "org.relaxcg.sc.common"})
public class UserMain {
    public static void main(String[] args) {
        SpringApplication.run(UserMain.class, args);
    }

    @GetMapping
    public Result<String> get() {
        return Result.ok("s");
    }
}

@Component
class Runner implements CommandLineRunner {
    @Resource
    private AccountMapper accountMapper;
    @Override
    public void run(String... args) throws Exception {
        // val account = new Account();
        // account.setPhone("10000000000");
        // account.setPassword(BCrypt.hashpw("admin123!!"));
        // accountMapper.insert(account);
        System.out.println(BCrypt.hashpw("admin123!!"));
    }
}