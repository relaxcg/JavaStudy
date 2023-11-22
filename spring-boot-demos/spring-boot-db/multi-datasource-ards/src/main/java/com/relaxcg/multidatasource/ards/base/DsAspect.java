package com.relaxcg.multidatasource.ards.base;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author relaxcg
 * @date 2023/11/22 14:49
 */
@Slf4j
@Aspect
@Component
public class DsAspect {

    @Pointcut("@within(DS)")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        DS ds = method.getAnnotation(DS.class);
        if (ds == null) {
            ds = method.getDeclaringClass().getAnnotation(DS.class);
        }
        if (ds != null) {
            log.info("current datasource is:{}", ds.value().getDsName());
            DataSourceContext.set(ds.value().getDsName());
        }
        try {
            return joinPoint.proceed();
        } finally {
            if (ds != null) {
                DataSourceContext.remove();
            }
        }
    }
}
