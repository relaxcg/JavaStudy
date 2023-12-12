package org.relaxcg.sc.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author relaxcg
 * @date 2023/11/30 11:40
 */
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan(basePackages = "org.relaxcg.sc.order.mapper")
@SpringBootApplication(scanBasePackages = {"org.relaxcg.sc.order", "org.relaxcg.sc.common"})
public class OrderMain {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain.class, args);
    }
}