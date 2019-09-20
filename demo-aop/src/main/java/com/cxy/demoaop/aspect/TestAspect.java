package com.cxy.demoaop.aspect;

import com.cxy.demoaop.exceptionhandler.TestException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.weaver.tools.PointcutPrimitive;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.stereotype.Component;


/**
 * Note:
 * Aspect：应用程序的某个模块化的关注点；通常是日志、权限、事务、打点、监控等。
 * JointPoint：连接点，程序执行过程中明确的点，一般是方法的调用。
 * Pointcut: 切点。指定施加于满足指定表达式的方法集合。Spring 默认使用 AspectJ pointcut 表达式。
 * Advance: 通知。指定在方法执行的什么时机，不同的Advance对应不同的切面方法；有before,after,afterReturning,afterThrowing,around。
 * TargetObject: 目标对象。通过Pointcut表达式指定的将被通知执行切面逻辑的实际对象。
 * AOP proxy: 代理对象。由AOP框架创建的代理，用于回调实际对象的方法，并执行切面逻辑。Spring实现中，若目标对象实现了至少一个接口，则使用JDK动态代理，否则使用 CGLIB 代理。优先使用JDK动态代理。
 * Weaving：织入。将切面类与应用对象关联起来。Spring使用运行时织入。
 * 通常 Pointcut 和 Advance 联合使用。即在方法上加上 @Advance(@Pointcut)
 *
 * 使用execution 定义AspectJ表达式 {@link PointcutPrimitive}{@link AspectJExpressionPointcut}
 * ?代表可省略 ,*表示任意值,全路径类名
 *
 *           修饰符              *返回值匹配      类路径匹配             *方法名匹配(参数名匹配,逗号隔开 异常类型匹配
 * execution(modifiers-pattern? ret-type-pattern declaring-type-pattern? name-pattern(param-pattern) throws-pattern?)
 *  (,String) 匹配两个参数,第一个是任意类型,第二个是String
 *  *          (..)表示0或任意参数)
 *  *          (set,get)代表xx开头的方法
 */
@Aspect
@Component("testAspect")
@Slf4j(topic = "Logger")
public class TestAspect {

    //定义切入点 * 任意返回值 ,任意类，任意方法， ..任意参数
    @Pointcut("execution(* com.cxy.demoaop.service.*.*(..))")
    public void pointCut(){
    }

    //定义切入点带有注解Test1
    @Pointcut("@annotation(com.cxy.demoaop.aspect.Test1)")
    public void pointCut1(){
    }

    //定义切入点带有注解Test2
    @Pointcut("@annotation(com.cxy.demoaop.aspect.Test2)")
    public void pointCut2(){
    }

    /**
     * 切点定义完了，Q:何时匹配? A:a方法调用了b,c方法,a方法匹配到了，利用代理对象执行，bc不会进入切面逻辑,还有
     * 被切面方法调用的内部方法；
     * final 方法；
     * private 方法；
     * 静态方法。
     * @param joinPoint
     */

    //前置通知
    @Before("pointCut()")
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

    /**
     * 环绕通知,使用|| , && ,!  来组合复杂的策略
     */

    //test1和test2注解同时出现
    @Around("pointCut1()"+"&& pointCut2()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature =  (MethodSignature)joinPoint.getSignature();
        String methodName = methodSignature.getMethod().getName();
        Object obj = null;
        //继续执行
        obj =  joinPoint.proceed();
        log.info(methodName+"after invoke joinPoint.proceed()");

        return obj;
    }




}
