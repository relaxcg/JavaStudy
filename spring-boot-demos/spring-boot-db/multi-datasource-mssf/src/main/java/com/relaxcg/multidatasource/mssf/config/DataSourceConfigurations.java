package com.relaxcg.multidatasource.mssf.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
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
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author relaxcg
 * @date 2023/11/21 17:27
 */
@Configuration
public class DataSourceConfigurations {

    @Configuration
    @MapperScan(basePackages = "com.relaxcg.multidatasource.mssf.mapper.ds1",
            sqlSessionFactoryRef = "ds1SqlSessionFactory")
    public static class Ds1Configuration {
        public static final String DS1_MAPPER_LOCATION = "classpath*:mapper/ds1/*.xml";


        @Primary
        @Bean("ds1DataSource")
        @ConfigurationProperties(prefix = "spring.datasource.ds1")
        public DataSource ds1DataSource() {
            return DataSourceBuilder.create().build();
        }

        @Primary
        @Bean("ds1TransactionManager")
        public PlatformTransactionManager ds1TransactionManager(@Qualifier("ds1DataSource") DataSource dataSource) {
            return new DataSourceTransactionManager(dataSource);
        }

        @Primary
        @Bean(name = "ds1SqlSessionFactory")
        public SqlSessionFactory ds1SqlSessionFactory(@Qualifier("ds1DataSource") DataSource dataSource) throws Exception {
            MybatisSqlSessionFactoryBean sessionFactoryBean = new MybatisSqlSessionFactoryBean();
            sessionFactoryBean.setDataSource(dataSource);
            sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(DS1_MAPPER_LOCATION));
            return sessionFactoryBean.getObject();
        }
    }

    @Configuration
    @MapperScan(basePackages = "com.relaxcg.multidatasource.mssf.mapper.ds2",
            sqlSessionFactoryRef = "ds2SqlSessionFactory")
    public static class Ds2Configuration {
        public static final String DS2_MAPPER_LOCATION = "classpath*:mapper/ds2/*.xml";

        @Bean
        @ConfigurationProperties(prefix = "spring.datasource.ds2")
        public DataSource ds2DataSource() {
            return DataSourceBuilder.create().build();
        }

        @Bean
        public PlatformTransactionManager ds2TransactionManager(@Qualifier("ds2DataSource") DataSource dataSource) {
            return new DataSourceTransactionManager(dataSource);
        }

        @Bean(name = "ds2SqlSessionFactory")
        public SqlSessionFactory ds2SqlSessionFactory(@Qualifier("ds2DataSource") DataSource dataSource) throws Exception {
            MybatisSqlSessionFactoryBean sessionFactoryBean = new MybatisSqlSessionFactoryBean();
            sessionFactoryBean.setDataSource(dataSource);
            sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(DS2_MAPPER_LOCATION));
            return sessionFactoryBean.getObject();
        }

    }
}
