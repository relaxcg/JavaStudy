package com.relaxcg.multidatasource.mssf.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author relaxcg
 * @date 2023/11/23 10:32
 */
@Slf4j
public class DataSourceBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar, EnvironmentAware {
    private final Map<Object, Object> dataSourceMap = new HashMap<>();

    @Override
    public void setEnvironment(Environment environment) {
        String dsNames = environment.getProperty("spring.datasource.names");
        Arrays.stream(dsNames.split(",")).map(String::trim)
                .forEach(dsName -> {
                    buildDataSource(dsName, environment);
                });
    }

    private void buildDataSource(String dsName, Environment environment) {
        String prefix = "spring.datasource." + dsName;
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setPoolName(dsName);
        hikariConfig.setDataSourceClassName(environment.getProperty(prefix + "driver-class-name"));
        hikariConfig.setJdbcUrl(environment.getProperty(prefix + ".jdbc-url"));
        hikariConfig.setUsername(environment.getProperty(prefix + ".username"));
        hikariConfig.setPassword(environment.getProperty(prefix + ".password"));

        dataSourceMap.put(dsName, hikariConfig);
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        boolean isFirst = true;
        for (Map.Entry<Object, Object> entry : dataSourceMap.entrySet()) {

            ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues();
            constructorArgumentValues.addIndexedArgumentValue(0, entry.getValue());

            GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
            beanDefinition.setBeanClass(HikariDataSource.class);
            beanDefinition.setConstructorArgumentValues(constructorArgumentValues);
            if (isFirst) {
                beanDefinition.setPrimary(true);
                isFirst = false;
            }
            registry.registerBeanDefinition(entry.getKey() + "DataSource", beanDefinition);
        }
    }
}
