package org.relaxcg.sc.common.utils;

import com.google.common.collect.Lists;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author relaxcg
 * @date 2023/11/8 18:09
 */
public class BeanUtils extends org.springframework.beans.BeanUtils {
    @FunctionalInterface
    public interface CopyStrategy<S, T> {

        void strategy(S source, T target);
    }

    public static <S, T> T copy(@Nullable S source, @NonNull Class<T> tClass) {
        if (source == null) {
            return null;
        }
        return ClassUtils.newInstanceOpt(tClass)
                .map(target -> {
                    copyProperties(source, target);
                    return target;
                }).orElse(null);
    }

    public static <S, T> T copy(@Nullable S source, @NonNull Class<T> tClass, @NonNull Consumer<T> strategy) {
        if (source == null) {
            return null;
        }
        return ClassUtils.newInstanceOpt(tClass)
                .map(target -> {
                    copyProperties(source, target);
                    strategy.accept(target);
                    return target;
                }).orElse(null);
    }

    public static <S, T> T copy(@Nullable S source, @NonNull Class<T> tClass, @NonNull CopyStrategy<S, T> strategy) {
        if (source == null) {
            return null;
        }
        return ClassUtils.newInstanceOpt(tClass)
                .map(target -> {
                    copyProperties(source, target);
                    strategy.strategy(source, target);
                    return target;
                }).orElse(null);
    }

    public static <T> T set(@Nullable T target, @NonNull Consumer<T> strategy) {
        if (target != null) {
            strategy.accept(target);
        }
        return target;
    }

    public static <S, T> List<T> copyList(@Nullable List<S> source, @NonNull Class<T> tClass) {
        if (source != null) {
            return source.stream().map(s -> copy(s, tClass)).collect(Collectors.toList());
        }
        return Lists.newArrayList();
    }


}
