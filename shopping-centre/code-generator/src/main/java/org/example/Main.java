package org.example;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.Types;
import java.util.Collections;

/**
 * @author relaxcg
 * @date 2023/11/30 14:23
 */
public class Main {
    public static void main(String[] args) {
        String projectName = "sc-stock";
        String projectPath = "D:\\ideaProjects\\JavaStudy\\shopping-centre\\" + projectName;
        FastAutoGenerator.create("jdbc:mysql://localhost:3307/shopping-centre?characterEncoding=utf-8&allowMultiQueries=true&serverTimezone=GMT%2B8", "root", "password")
                .globalConfig(builder -> {
                    builder.author("relaxcg") // 设置作者
                            .outputDir(projectPath + "\\src\\main\\java"); // 指定输出目录
                })
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                    if (typeCode == Types.SMALLINT) {
                        // 自定义类型转换
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);

                }))
                .packageConfig(builder -> {
                    builder.parent("org.relaxcg.sc") // 设置父包名
                            .moduleName("stock") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D:\\ideaProjects\\JavaStudy\\shopping-centre\\"+projectName+"\\src\\main\\resources\\mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("sc_stock") // 设置需要生成的表名
                            .addTablePrefix("sc_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}