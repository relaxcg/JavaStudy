package com.relaxcg.limiter;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
// @SpringBootTest
class SpringBootLimiterApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testSmoothBursty() throws InterruptedException {
        TestGuavaLimiter.testSmoothBursty();
    }

    @Test
    public void testSmoothWarmingUp() throws InterruptedException {
        TestGuavaLimiter.testSmoothWarmingUp();
    }

}
