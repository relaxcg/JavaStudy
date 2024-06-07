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

    }
}
