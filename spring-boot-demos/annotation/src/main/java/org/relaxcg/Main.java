package org.relaxcg;

import org.relaxcg.annotations.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author relaxcg
 * @date 2024/3/12 15:25
 */
@RestController
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }


    @GetMapping("/test")
    @Test
    public String get() {
        return "test";
    }
}