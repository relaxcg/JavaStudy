package org.relaxcg.sc.common.utils;

import com.google.common.collect.Lists;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
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

    public static <S, T> T copy(@Nullable S source, @NonNull Supplier<T> targetSupplier) {
        return copy(source, targetSupplier, (Consumer<T>) null);
    }

    public static <S, T> T copy(@Nullable S source, @NonNull Supplier<T> targetSupplier, @Nullable Consumer<T> strategy) {
        if (source == null) {
            return null;
        }
        T target = targetSupplier.get();
        copyProperties(source, target);
        if (strategy != null) {
            strategy.accept(target);
        }
        return target;
    }

    public static <S, T> T copy(@Nullable S source, @NonNull Supplier<T> targetSupplier, @NonNull CopyStrategy<S, T> strategy) {
        if (source == null) {
            return null;
        }
        T target = targetSupplier.get();
        copyProperties(source, target);
        strategy.strategy(source, target);
        return target;
    }


    public static <T> T setObj(@Nullable T target, @NonNull Consumer<T> strategy) {
        if (target != null) {
            strategy.accept(target);
        }
        return target;
    }

    public static <T> T set(@Nullable Supplier<T> targetSupplier, @NonNull Consumer<T> strategy) {
        if (targetSupplier != null) {
            T t = targetSupplier.get();
            if (t != null) {
                strategy.accept(t);
                return t;
            }
        }
        return null;
    }

    public static <S, T> List<T> copyList(@Nullable List<S> source, @NonNull Supplier<T> targetSupplier) {
        return copyList(source, targetSupplier, (Consumer<T>) null);
    }


    public static <S, T> List<T> copyList(@Nullable List<S> source, @NonNull Supplier<T> targetSupplier, @NonNull CopyStrategy<S, T> strategy) {
        if (source != null) {
            return source.stream().map(s -> copy(s, targetSupplier, strategy)).collect(Collectors.toList());
        }
        return Lists.newArrayList();
    }

    public static <S, T> List<T> copyList(@Nullable List<S> source, @NonNull Supplier<T> targetSupplier, Consumer<T> strategy) {
        if (source != null) {
            return source.stream().map(s -> copy(s, targetSupplier, strategy)).collect(Collectors.toList());
        }
        return Lists.newArrayList();
    }

}
