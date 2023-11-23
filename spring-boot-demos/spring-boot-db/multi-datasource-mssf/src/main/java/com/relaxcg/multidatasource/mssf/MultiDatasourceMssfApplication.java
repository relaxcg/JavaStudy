package com.relaxcg.multidatasource.mssf;

import com.relaxcg.multidatasource.mssf.config.DataSourceBeanDefinitionRegistrar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(DataSourceBeanDefinitionRegistrar.class)
@SpringBootApplication
public class MultiDatasourceMssfApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultiDatasourceMssfApplication.class, args);
    }

}
