package org.relaxcg.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author relaxcg
 * @date 2024/3/19 16:25
 */
@RestController
@SpringBootApplication
public class ESMain {
    public static void main(String[] args) {
        SpringApplication.run(ESMain.class, args);
    }

    @Resource
    private ESUtils esUtils;

    @GetMapping
    public void test() throws IOException {
        esUtils.query();
    }
}