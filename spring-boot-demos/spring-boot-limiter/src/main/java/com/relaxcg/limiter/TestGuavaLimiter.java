package com.relaxcg.limiter;

import com.google.common.util.concurrent.RateLimiter;
import com.relaxcg.common.utils.ThreadUtils;
import lombok.extern.slf4j.Slf4j;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author relaxcg
 * @date 2023/11/20 16:42
 */
@Slf4j
public class TestGuavaLimiter {

    public static void testSmoothBursty() throws InterruptedException {
        RateLimiter rateLimiter = RateLimiter.create(5);
        ThreadUtils.sleep(1000);
        log.info("start");
        log.info("第一次请求消耗的时间:{}", rateLimiter.acquire(5));
        log.info("第二次请求消耗的时间:{}", rateLimiter.acquire(1));
        log.info("第三次请求消耗的时间:{}", rateLimiter.acquire(1));
    }

    public static void testSmoothWarmingUp() throws InterruptedException {
        RateLimiter rateLimiter = RateLimiter.create(5, 3, TimeUnit.SECONDS);
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                log.info("result:{}", rateLimiter.acquire());
            });
            thread.start();
            thread.join();
        }
    }

}
