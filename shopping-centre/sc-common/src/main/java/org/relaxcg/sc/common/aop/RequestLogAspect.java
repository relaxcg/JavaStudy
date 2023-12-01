package org.relaxcg.sc.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author relaxcg
 * @date 2023/11/17 14:04
 */
@Slf4j
@Aspect
@Component

public class RequestLogAspect {

    @Pointcut("execution(public * org.relaxcg..controller..*(..))")
    public void log() {
    }

    @Around("log()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        // HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        log.info("Class Method: {}.{}", joinPoint.getSignature().getDeclaringTypeName(), methodSignature.getName());
        log.info("Request Args: {}", Arrays.toString(joinPoint.getArgs()));
        return joinPoint.proceed();
    }
}