package com.cxy.demoaop.aspect;

import com.cxy.demoaop.exceptionhandler.TestException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component("testAspect")
@Slf4j(topic = "Logger")
public class TestAspect {

    //定义切入点 * 任意返回值 ,任意类，任意方法， ..任意参数
    @Pointcut("execution(* com.cxy.demoaop.service.*.*(..))")
    public void pointCut(){
    }
    //前置通知
    @Before(value = "pointCut()")
    public void beforeAdvice(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        log.info(name+"开始执行");
    }
    //后置通知
    @After(value = "pointCut()")
    public void afterAdvice(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        log.info(name+"结束执行");
    }

    //返回通知,只能处理返回值为Long的方法
    @AfterReturning(value = "pointCut()",returning = "result")
    public void afterReturningLong(JoinPoint joinPoint,Long result){
        String name = joinPoint.getSignature().getName();
        log.info(name+"返回Long类型值"+result);
    }
    //返回通知,只能处理返回值为String的方法
    @AfterReturning(value = "pointCut()",returning = "result")
    public void afterReturningString(JoinPoint joinPoint,String result){
        String name = joinPoint.getSignature().getName();
        log.info(name+"返回String类型值"+result);
    }

    //异常通知,只能处理TestException的方法
    @AfterThrowing(value = "pointCut()",throwing = "e")
    public void afterReturning(JoinPoint joinPoint, TestException e){
        String name = joinPoint.getSignature().getName();
        log.info(name+e.getMessage());
    }

    //环绕通知
//    @Around(value = "pointCut()")
//    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
//
//        //继续执行
//        return joinPoint.proceed();
//    }




}
