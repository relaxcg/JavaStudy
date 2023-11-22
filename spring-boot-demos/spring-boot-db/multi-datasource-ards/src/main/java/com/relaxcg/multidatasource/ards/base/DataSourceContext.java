package com.relaxcg.multidatasource.ards.base;

/**
 * @author relaxcg
 * @date 2023/11/22 14:22
 */
public class DataSourceContext {
    private final static ThreadLocal<String> DATA_SOURCE_HOLDER = new ThreadLocal<>();

    public static void set(String dsName) {
        DATA_SOURCE_HOLDER.set(dsName);
    }

    public static String get() {
        return DATA_SOURCE_HOLDER.get();
    }

    public static void remove() {
        DATA_SOURCE_HOLDER.remove();
    }
}
