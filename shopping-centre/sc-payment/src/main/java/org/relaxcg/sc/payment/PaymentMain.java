package org.relaxcg.sc.payment;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author relaxcg
 * @date 2023/11/30 11:40
 */
@MapperScan(basePackages = "org.relaxcg.sc.payment.mapper")
@SpringBootApplication(scanBasePackages = {"org.relaxcg.sc.payment", "org.relaxcg.sc.common"})
public class PaymentMain {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain.class, args);
    }
}