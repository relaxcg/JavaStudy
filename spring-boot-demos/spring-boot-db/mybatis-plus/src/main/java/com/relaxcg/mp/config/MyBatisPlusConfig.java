// package com.relaxcg.mp.config;
//
// import com.baomidou.mybatisplus.core.config.GlobalConfig;
// import org.apache.ibatis.type.TypeHandler;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
//
// @Configuration
// public class MyBatisPlusConfig {
//     @Bean
//     public GlobalConfig globalConfig() {
//         GlobalConfig config = new GlobalConfig();
//         GlobalConfig.DbConfig dbConfig = new GlobalConfig.DbConfig();
//         // dbConfig.s
//         // config.setDbConfig()
//         // // config.setMetaObjectHandler(new MyMetaObjectHandler());
//         // config.setDbType(DbType.POSTGRESQL); // 若适用，设置数据库类型
//         config.setTypeHandlers(new TypeHandler[]{new JsonbTypeHandler(Object.class)}); // 注册自定义 TypeHandler
//         return config;
//     }
//
//     // ... 其他配置
// }
