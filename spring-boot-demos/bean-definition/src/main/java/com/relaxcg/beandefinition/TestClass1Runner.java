package com.relaxcg.beandefinition;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author relaxcg
 * @date 2023/11/23 14:14
 */
@Slf4j
@Component
public class TestClass1Runner implements CommandLineRunner {
    @Autowired(required = false)
    private List<TestClass1> list;

    @Override
    public void run(String... args) throws Exception {
        if (list == null) {
            log.info("list is null");
        } else {
            list.forEach(l -> {
                log.info("aa:{}", l.toString());
            });
        }
    }
}
