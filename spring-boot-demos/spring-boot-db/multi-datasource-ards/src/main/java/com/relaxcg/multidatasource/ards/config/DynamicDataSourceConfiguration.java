package com.relaxcg.multidatasource.ards.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.relaxcg.multidatasource.ards.enums.DataSources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author relaxcg
 * @date 2023/11/22 11:37
 */
@Configuration
@MapperScan("com.relaxcg.multidatasource.ards.mapper")
public class DynamicDataSourceConfiguration {
    private static final String MAPPER_LOCATION = "classpath*:mapper/*.xml";

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.ds1")
    public DataSource ds1DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.ds2")
    public DataSource ds2DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public DynamicDataSource dynamicDataSource(@Qualifier("ds1DataSource") DataSource ds1DataSource,
                                               @Qualifier("ds2DataSource") DataSource ds2DataSource) {
        Map<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put(DataSources.DS1.getDsName(), ds1DataSource);
        targetDataSource.put(DataSources.DS2.getDsName(), ds2DataSource);

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(targetDataSource);
        dynamicDataSource.setDefaultTargetDataSource(ds1DataSource);
        return dynamicDataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") DynamicDataSource dynamicDataSource) throws Exception {
        MybatisSqlSessionFactoryBean sessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dynamicDataSource);
        // sessionFactoryBean.setTransactionFactory(new MultiDataSourceTransactionFactory());
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
        return sessionFactoryBean.getObject();
    }
}
