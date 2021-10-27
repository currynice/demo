package com.cxy.demo.demolog.aspect;


import com.cxy.demo.demolog.spel.LogRecordContext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import java.lang.reflect.Method;
import java.util.*;


/**
 * 实现操作日志的增强逻辑  toto 转为 auto_configuration
 * @author 程新宇
 *
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    private LogRecordOperationSource logRecordOperationSource = new LogRecordOperationSource();


    /**
     * 切点:匹配包含 @LogRecord method
     */
    @Pointcut("@annotation(com.cxy.demo.demolog.annotation.LogRecord)")
    public void logRecordPointcut (){

    }

    /**
     * 切面增强逻辑
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("logRecordPointcut()")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {

        //getTargetClass
        Class<?> targetClass = joinPoint.getTarget().getClass();

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        //标注@LogRecord的方法
        Method method = methodSignature.getMethod();

        //方法 的参数列表
        Object[] args = joinPoint.getArgs();


        Object result = null;

        MethodExecuteResult methodExecuteResult = new MethodExecuteResult(true, null, "");

        LogRecordContext.putEmptySpan();
        LogRecordOp operation = new LogRecordOp();

        Map<String, String> functionNameAndReturnMap = new HashMap<>();

        try {
            //解析出注解上的参数
            operation = logRecordOperationSource.computeLogRecordOperations(method, targetClass);
            List<String> spElTemplates = getBeforeExecuteFunctionTemplate(operation);
            //业务逻辑执行前,执行SpEL解析, 自定义函数解析
            functionNameAndReturnMap = processBeforeExecuteFunctionTemplate(spElTemplates, targetClass, method, args);
        } catch (Exception e) {
            log.error("log record parse before function exception", e);
        }


        try {
            result = joinPoint.proceed();
        } catch (Exception e) {
            //失败模板
            methodExecuteResult = new MethodExecuteResult(false, e, e.getMessage());
        }
        try {
                recordExecute(result, method, args, operation, targetClass,
                        methodExecuteResult.isSuccess(), methodExecuteResult.getErrorMsg(), functionNameAndReturnMap);

        } catch (Exception t) {
            //'记录日志' 发生错误，不要影响业务
            log.error("log record parse exception", t);
        } finally {
            LogRecordContext.clear();
        }
        if (methodExecuteResult.getThrowable() != null) {
            throw methodExecuteResult.getThrowable();
        }
        return result;
    }


    private List<String> getBeforeExecuteFunctionTemplate(LogRecordOp operation) {
        return new ArrayList<>();//todo
    }


    private Map<String, String> processBeforeExecuteFunctionTemplate(List<String> spElTemplates, Class<?> targetClass, Method method, Object args) {

        return new HashMap<>();// todo

    }



    /**
     * 记录操作日志
     * @param result
     * @param method
     * @param args
     * @param operation
     * @param targetClass
     * @param success
     * @param errorMsg
     * @param functionNameAndReturnMap
     */
    private void recordExecute(Object result, Method method, Object args, LogRecordOp operation, Class<?> targetClass, boolean success, String errorMsg, Map<String, String> functionNameAndReturnMap) {
    }


}
