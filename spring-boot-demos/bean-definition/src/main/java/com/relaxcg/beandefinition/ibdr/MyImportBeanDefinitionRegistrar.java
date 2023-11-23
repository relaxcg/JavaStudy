package com.relaxcg.beandefinition.ibdr;

import com.relaxcg.beandefinition.TestClass1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import java.util.LinkedHashMap;

/**
 * @author relaxcg
 * @date 2023/11/23 11:39
 */
@Slf4j
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar, EnvironmentAware {
    private LinkedHashMap<String, Object> properties;

    @Override
    public void setEnvironment(Environment environment) {
        BindResult<LinkedHashMap> bindResult = Binder.get(environment).bind("ibdr", LinkedHashMap.class);
        properties = bindResult.get();
        log.info("ibdr properties set");
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        properties.forEach((k, v) -> {
            log.info("{}, {}, {}", k, v, v.getClass());
            GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
            beanDefinition.setBeanClass(TestClass1.class);
            ConstructorArgumentValues cav = new ConstructorArgumentValues();
            cav.addIndexedArgumentValue(0, k);
            beanDefinition.setConstructorArgumentValues(cav);
            if (v instanceof LinkedHashMap<?, ?> realProperties) {
                realProperties.forEach((k1, v1) -> {
                    beanDefinition.getPropertyValues().add((String) k1, v1);
                });
                registry.registerBeanDefinition("ibdr"+k, beanDefinition);
            }
        });

        // String name = environment.getProperty("ibdr.name");
        // String ext = environment.getProperty("ibdr.ext");
        // beanDefinition.setBeanClass(TestClass1.class);
        // ConstructorArgumentValues cav = new ConstructorArgumentValues();
        // cav.addIndexedArgumentValue(0, name);
        // beanDefinition.getPropertyValues().add("ext", ext);
        //
        // registry.registerBeanDefinition("testClass1", beanDefinition);
        log.info("ibdr");
    }
}
