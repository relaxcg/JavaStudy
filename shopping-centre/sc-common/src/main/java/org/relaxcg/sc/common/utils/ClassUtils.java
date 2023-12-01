package org.relaxcg.sc.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

/**
 * @author relaxcg
 * @date 2023/11/8 18:12
 */
@Slf4j
public class ClassUtils {

    public static <T> T newInstance(Class<T> tClass) {
        try {
            return tClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            log.error("null");
            return null;
        }
    }

    public static <T> Optional<T> newInstanceOpt(Class<T> tClass) {
        return Optional.ofNullable(newInstance(tClass));
    }
}
