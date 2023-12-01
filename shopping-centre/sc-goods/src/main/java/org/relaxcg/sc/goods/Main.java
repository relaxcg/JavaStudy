package org.relaxcg.sc.goods;

import org.relaxcg.sc.common.web.Result;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author relaxcg
 * @date 2023/11/30 11:39
 */
@RestController
@SpringBootApplication(scanBasePackages ={"org.relaxcg.sc.goods", "org.relaxcg.sc.common"})
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping
    public Result<String> get() {
        return Result.ok("s");
    }
}