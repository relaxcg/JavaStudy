package org.relaxcg.sc.goods;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author relaxcg
 * @date 2023/11/30 11:39
 */

@MapperScan(basePackages = {"org.relaxcg.sc.goods.mapper"})
@SpringBootApplication(scanBasePackages = {"org.relaxcg.sc.goods", "org.relaxcg.sc.common"})
public class GoodsMain {
    public static void main(String[] args) {
        SpringApplication.run(GoodsMain.class, args);
    }

}