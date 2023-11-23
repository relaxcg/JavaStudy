package com.relaxcg.beandefinition.bdrpp;

import com.relaxcg.beandefinition.TestClass1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

/**
 * @author relaxcg
 * @date 2023/11/23 11:46
 */
@Component
@Slf4j
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor, EnvironmentAware {
    private LinkedHashMap<String, Object> properties;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
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
                registry.registerBeanDefinition("bdrpp" + k, beanDefinition);
            }
        });
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    @Override
    public void setEnvironment(Environment environment) {
        BindResult<LinkedHashMap> bindResult = Binder.get(environment).bind("bdrpp", LinkedHashMap.class);
        properties = bindResult.get();
        log.info("bdrpp properties set");
    }
}
