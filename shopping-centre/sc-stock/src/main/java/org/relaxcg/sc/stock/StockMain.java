package org.relaxcg.sc.stock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author relaxcg
 * @date 2023/11/30 11:39
 */
@MapperScan(basePackages = {"org.relaxcg.sc.stock.mapper"})
@SpringBootApplication(scanBasePackages = {"org.relaxcg.sc.stock", "org.relaxcg.sc.common"})
public class StockMain {
    public static void main(String[] args) {
        SpringApplication.run(StockMain.class, args);
    }
}