package com.cxy.demoaop.aspect.logwithnotannotation.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAspect {
    //    @Around("execution(* com.cxy.demoaop.aspect.logwithnotannotation.service..*(..))")
    @Around("repositoryOps()")
    public Object log(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        String name = "-";
        String result = "Y";
        try {
            name = pjp.getSignature().toShortString();
            return pjp.proceed();
        } catch (Throwable t) {
            result = "N";
            throw t;
        } finally {
            long endTime = System.currentTimeMillis();
            log.info("{};{};{}ms", name, result, endTime - startTime);
        }
    }

    //所有logwithnotannotation下service方法
    @Pointcut("execution(* com.cxy.demoaop.aspect.logwithnotannotation.service..*(..))")
    private void repositoryOps() {
    }
}
