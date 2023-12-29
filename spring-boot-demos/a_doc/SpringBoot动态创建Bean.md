### 根据配置文件动态创建Bean

在开发的过程中，往往会遇到需要根据配置来创建 Bean。以下是几种创建Bean的方式。

#### 背景举例

当我们需要配置多个数据源时，数据源参数（yml）

```yaml
spring:
  datasource:
    names: ds1, ds2
    ds1:
      jdbc-url: jdbc:mysql://localhost:3307/spring-boot-demos?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true
      username: root
      password: password
      driver-class-name: com.mysql.cj.jdbc.Driver
    ds2:
      jdbc-url: jdbc:mysql://127.0.0.1:3308/spring-boot-demos?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true
      username: root
      password: password
      driver-class-name: com.mysql.cj.jdbc.Driver
```

#### @Bean + @ConfigurationProperties

使用这种方式，需要在配置类里配置 Bean

```java
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfigurations {

    @Primary
    @Bean("ds1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.ds1")
    public DataSource ds1DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("ds1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.ds1")
    public DataSource ds1DataSource() {
        return DataSourceBuilder.create().build();
    }
}
```

如此即可配置出相应的数据源，但这种方式有个缺点，每多一个数据源配置，需要多定义一个 Bean。（可能还需要定义不同的
SqlSessionFactory 和 PlatformTransactionManager）。
显得很死板。有没有一种动态的方式来进行配置呢？

#### ImportBeanDefinitionRegistrar + EnvironmentAware

对于类上有 @Import 注解的类，SpringBoot 会对其进行导入，如果值是 ImportBeanDefinitionRegistrar 的实现类，则会调用其
registerBeanDefinitions 方法。

1. 自定义 ImportBeanDefinitionRegistrar 实现类

```java

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
            if (v instanceof LinkedHashMap<?, ?> properties) {
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
```

2. 使用

```java
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(DataSourceBeanDefinitionRegistrar.class)
@Configuration
public class Config {

}

```

#### BeanDefinitionRegistryPostProcessor + EnvironmentAware

当执行到 BeanDefinitionRegistryPostProcessor.postProcessBeanDefinitionRegistry()，可以将自定义的 BeanDefinition 注册到
BeanDefinitionRegistry 中去。
整体的实现逻辑和上面一种没有区别，只是加载的时机不同罢了，ImportBeanDefinitionRegistrar 会先一步加载。

```java
@Component
@Slf4j
public class DataSourceBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor, EnvironmentAware {
    private final Map<Object, Object> dataSourceMap = new HashMap<>();

    @Override
    public void setEnvironment(Environment environment) {
        // 通过继承 EnvironmentAware 获取 environment，通过 Binder 即可读取配置
        LinkedHashMap<String, Object> allProperties = Binder.get(environment).bind("spring.datasource", LinkedHashMap.class).get();
        buildDataSource(allProperties);
    }

    private void buildDataSource(LinkedHashMap<String, Object> allProperties) {
        allProperties.forEach((dsName, v) -> {
            if (v instanceof LinkedHashMap<?, ?> properties) {
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
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
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

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
```
BeanDefinitionRegistryPostProcessor 的使用，只需要加上 @Component 注解即可。

