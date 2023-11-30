package com.relaxcg.redis;

import com.relaxcg.redis.config.utils.RedisUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class SpringBootRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRedisApplication.class, args);
    }

}

@Component
class Runner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        RedisUtils.set("spring-boot:redis", "hello world");
    }
}
