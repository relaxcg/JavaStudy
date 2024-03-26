package com.relaxcg.multidatasource.ards.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author relaxcg
 * @date 2023/11/22 11:37
 */
@Configuration
@MapperScan("com.relaxcg.multidatasource.ards.mapper")
public class DynamicDataSourceConfiguration {
    private static final String MAPPER_LOCATION = "classpath*:mapper/*.xml";

    @Primary
    @Bean(initMethod = "init")
    @ConfigurationProperties(prefix = "spring.datasource.druid.ds1")
    public DataSource ds1DataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    public JdbcTemplate jdbcTemplate1(@Qualifier("ds1DataSource") DataSource ds1DataSource) {
        return new JdbcTemplate(ds1DataSource);
    }

    // @Bean
    // @ConfigurationProperties(prefix = "spring.datasource.druid.ds2")
    // public DataSource ds2DataSource() {
    //     return DruidDataSourceBuilder.create().build();
    // }
    //
    // @Bean
    // public JdbcTemplate jdbcTemplate2(@Qualifier("ds2DataSource") DataSource ds2DataSource) {
    //     return new JdbcTemplate(ds2DataSource);
    // }

    // @Bean
    // public DynamicDataSource dynamicDataSource(@Qualifier("ds1DataSource") DataSource ds1DataSource,
    //                                            @Qualifier("ds2DataSource") DataSource ds2DataSource) {
    //     Map<Object, Object> targetDataSource = new HashMap<>();
    //     targetDataSource.put(DataSources.DS1.getDsName(), ds1DataSource);
    //     targetDataSource.put(DataSources.DS2.getDsName(), ds2DataSource);
    //
    //     DynamicDataSource dynamicDataSource = new DynamicDataSource();
    //     dynamicDataSource.setTargetDataSources(targetDataSource);
    //     dynamicDataSource.setDefaultTargetDataSource(ds1DataSource);
    //     return dynamicDataSource;
    // }
    //
    // @Bean
    // public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") DynamicDataSource dynamicDataSource) throws Exception {
    //     MybatisSqlSessionFactoryBean sessionFactoryBean = new MybatisSqlSessionFactoryBean();
    //     sessionFactoryBean.setDataSource(dynamicDataSource);
    //     // sessionFactoryBean.setTransactionFactory(new MultiDataSourceTransactionFactory());
    //     sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
    //     return sessionFactoryBean.getObject();
    // }
}
