package org.relaxcg;

import java.nio.charset.Charset;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author relaxcg
 * @date 2024/2/22 9:45
 */
public class ExecutorsDemos {

    public static void testNewSingleThreadScheduledExecutor() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> {
            System.out.println("Task started");
            int result = 1 / 0; // 这里会抛出 ArithmeticException
            System.out.println("Task completed");
        }, 1, 1, TimeUnit.SECONDS);
    }


    public static void main(String[] args) {
        // testNewSingleThreadScheduledExecutor();
        // ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        //
        // executor.scheduleAtFixedRate(() -> {
        //     try {
        //         // 模拟任务执行过程中发生异常
        //         System.out.println("Task started");
        //         int result = 1 / 0; // 这里会抛出 ArithmeticException
        //         System.out.println("Task completed");
        //     } catch (Exception e) {
        //         System.out.println("Task failed: " + e.getMessage());
        //     }
        // }, 0, 1, TimeUnit.SECONDS);

        // ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        //
        // executor.scheduleAtFixedRate(() -> {
        //         try {
        //             // 模拟任务执行过程中发生异常
        //             System.out.println("Task started");
        //             int result = 1 / 0; // 这里会抛出 ArithmeticException
        //             System.out.println("Task completed");
        //         } catch (Exception e) {
        //             System.out.println("Task failed: " + e.getMessage());
        //         }
        // }, 0, 1, TimeUnit.SECONDS);

        System.out.println(-1L ^ (-1L << 5));
        System.out.println(~(-1L << 5));
    }
}
