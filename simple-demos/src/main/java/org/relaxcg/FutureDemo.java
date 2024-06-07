package org.relaxcg;


import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

/**
 * @author relaxcg
 * @date 2024/4/14 11:35
 */
@Slf4j
public class FutureDemo {

    // static Logger log = LoggerFactory.getLogger(FutureDemo.class);

    public static void test1() {
        long a = System.currentTimeMillis();
        Random random = new Random();
        CompletableFuture<String>[] futures = new CompletableFuture[10];
        for (int i = 0; i < 10; i++) {
            futures[i] = CompletableFuture.supplyAsync(() -> {
                long s = System.currentTimeMillis();
                try {
                    Thread.sleep(random.nextInt(1000, 3000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                String n = String.valueOf(System.currentTimeMillis() - s);
                System.out.println(n + ":" + Thread.currentThread().getName());
                return n;
            });
        }
        CompletableFuture.allOf(futures).join();
        for (CompletableFuture<String> future : futures) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("a:" + (System.currentTimeMillis() - a));
        System.out.println("poolSize:" + ForkJoinPool.commonPool().getPoolSize());
    }

    public static void main(String[] args) {
        log.info("123");
        // test1();
        int i = 100;
        try {
            int n = i / 0;
        } catch (Exception e) {
            log.error("ggg:{}", i, e);
        }
    }
}
