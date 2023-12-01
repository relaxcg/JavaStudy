package org.relaxcg.sc.common.utils;

/**
 * @author relaxcg
 * @date 2023/11/17 14:05
 */
public class ThreadUtils {

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
