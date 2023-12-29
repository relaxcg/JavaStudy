package org.relaxcg.sc.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.relaxcg.sc.common.aop.annotation.VerifySign;
import org.springframework.stereotype.Component;

/**
 * @author relaxcg
 * @date 2023/12/23 15:46
 */
@Slf4j
@Aspect
@Component
public class VerifySignAspect {


    @Before("@annotation(verifySign) || @within(verifySign)")
    public void before(JoinPoint joinPoint, VerifySign verifySign) {
        log.info("verify sign...");
    }
}
