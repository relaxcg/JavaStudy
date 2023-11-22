package com.relaxcg.multidatasource.ards.base;

import com.relaxcg.multidatasource.ards.enums.DataSources;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author relaxcg
 * @date 2023/11/22 14:46
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface DS {

    DataSources value() default DataSources.DS1;
}
