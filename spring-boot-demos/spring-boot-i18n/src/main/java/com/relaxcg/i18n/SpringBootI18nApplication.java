package com.relaxcg.i18n;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.relaxcg.i18n", "com.relaxcg.common"})
public class SpringBootI18nApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootI18nApplication.class, args);
    }

}
