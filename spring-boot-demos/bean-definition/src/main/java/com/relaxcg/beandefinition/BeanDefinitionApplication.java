package com.relaxcg.beandefinition;

import com.relaxcg.beandefinition.ibdr.MyImportBeanDefinitionRegistrar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({MyImportBeanDefinitionRegistrar.class})
@SpringBootApplication
public class BeanDefinitionApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeanDefinitionApplication.class, args);
    }

}
