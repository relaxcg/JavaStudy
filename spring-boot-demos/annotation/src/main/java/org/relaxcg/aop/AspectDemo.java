package org.relaxcg.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.relaxcg.annotations.Test;
import org.springframework.stereotype.Component;

/**
 * @author relaxcg
 * @date 2024/3/12 15:27
 */
@Slf4j
@Aspect
@Component
public class AspectDemo {

    @Before("@within(test) || @annotation(test)")
    public void before(JoinPoint joinPoint, Test test) {
        log.info(test.value());
    }
}
