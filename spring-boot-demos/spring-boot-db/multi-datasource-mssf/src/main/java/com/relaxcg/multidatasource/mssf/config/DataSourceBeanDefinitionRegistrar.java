package com.relaxcg.multidatasource.mssf.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import java.util.HashMap;
import java.util.LinkedHashMap;
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
        // 通过继承 EnvironmentAware 获取 environment，通过 Binder 即可读取配置
        LinkedHashMap<String, Object> allProperties = Binder.get(environment).bind("spring.datasource", LinkedHashMap.class).get();
        buildDataSource(allProperties);
    }

    private void buildDataSource(LinkedHashMap<String, Object> allProperties) {
        allProperties.forEach((dsName, v) -> {
            if (v instanceof LinkedHashMap<?,?> properties) {
                // 根据我们的配置来看，v 所对应的一定是LinkedHashMap
                HikariConfig hikariConfig = new HikariConfig();
                hikariConfig.setPoolName(dsName);
                hikariConfig.setDriverClassName(properties.get("driver-class-name").toString());
                hikariConfig.setJdbcUrl(properties.get("jdbc-url").toString());
                hikariConfig.setUsername(properties.get("username").toString());
                hikariConfig.setPassword(properties.get("password").toString());
                dataSourceMap.put(dsName, hikariConfig);
            }
        });

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
