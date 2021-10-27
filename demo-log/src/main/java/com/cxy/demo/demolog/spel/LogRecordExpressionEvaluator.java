package com.cxy.demo.demolog.spel;

import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.context.expression.CachedExpressionEvaluator;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description: SpEL 解析类  </br>
 * Date: 2021/9/17 17:17
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
public class LogRecordExpressionEvaluator extends CachedExpressionEvaluator {


    /**
     * SpEL 将被解析成一个 Expression 表达式，然后根据传入的 Object 获取到对应的值，
     *
     * expressionCache 缓存方法、表达式(string) 和  Expression 的对应关系
     * 让方法注解上添加的 SpEL 表达式只解析一次。
     *
     * */
    private Map<ExpressionKey, Expression> expressionCache = new ConcurrentHashMap<>(64);


    /**
     *  targetMethodCache  缓存传入到 Expression 表达式的 Object。
     */
    private final Map<AnnotatedElementKey, Method> targetMethodCache = new ConcurrentHashMap<>(64);


    /**
     * getExpression 逻辑:从 expressionCache 中获取到 @LogRecordAnnotation 注解上的表达式的解析 Expression 的实例，
     * 然后调用 getValue 方法，getValue 传入一个 evalContext
     * @param conditionExpression
     * @param methodKey
     * @param evalContext
     * @return
     */
    public String parseExpression(String conditionExpression, AnnotatedElementKey methodKey, EvaluationContext evalContext) {
        return getExpression(this.expressionCache, methodKey, conditionExpression).getValue(evalContext, String.class);
    }




}
