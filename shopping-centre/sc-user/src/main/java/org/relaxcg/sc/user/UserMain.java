package org.relaxcg.sc.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author relaxcg
 * @date 2023/11/30 11:39
 */
@MapperScan(basePackages = {"org.relaxcg.sc.user.mapper"})
@SpringBootApplication(scanBasePackages = {"org.relaxcg.sc.user", "org.relaxcg.sc.common"})
public class UserMain {
    public static void main(String[] args) {
        SpringApplication.run(UserMain.class, args);
    }
}