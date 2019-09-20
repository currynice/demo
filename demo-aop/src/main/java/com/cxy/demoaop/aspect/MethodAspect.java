package com.cxy.demoaop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 记录方法的调用次数及耗时
 * IO阻塞操作：文件操作， DB操作，API操作， ES访问，Hbase访问；todo
 * 同步操作：lock, synchronized, 同步工具所施加的代码块等；todo
 * CPU耗时：大数据量的format, sort 等。todo
 * 提供了toString方法,注入上层后二次处理
 *
 */
@Aspect
@Component("methodAspect")
@Slf4j(topic = "Logger")
public class MethodAspect {
    /**
     * K:className-methodName
     * V:调用次数
     */
    private Map<String, Integer> methodCount = new ConcurrentHashMap();


    /**
     * 调用耗时
     * K:className-methodName
     * V:
     */
    private Map<String, List<Integer>> methodCost = new ConcurrentHashMap();

    /**
     * 将map的值排序
     * @param map
     * @param <K>
     * @param <V> 必要的话需要重写比较规则
     * @return
     */
    private static<K,V extends Comparable<? super V>> Map<K,V> sortByValue(Map<K,V> map){
        //有序的map
        Map<K,V> linkedMap = new LinkedHashMap<>();
        map.entrySet().stream().sorted(Comparator.comparing(m->m.getValue())).forEach(m-> linkedMap.put(m.getKey(),m.getValue()));
        return linkedMap;
    }


    //定义切点
    @Pointcut("@annotation(com.cxy.demoaop.aspect.MethodMeasure)")
    public void methodPointCut(){

    }

    @SuppressWarnings(value = "unchecked")
    @Around("methodPointCut()")
    public Object process(ProceedingJoinPoint joinPoint) {
        Object obj = null;
        //class名
        String className = joinPoint.getTarget().getClass().getSimpleName();
        //方法名
        String methodName = className + "_" + getMethodName(joinPoint);
        long startTime = System.currentTimeMillis();
        try {
            obj = joinPoint.proceed();
        } catch (Throwable t) {
            log.error(t.getMessage(), t);
        } finally {
            long costTime = System.currentTimeMillis() - startTime;
            log.info("method={}, cost_time={}", methodName, costTime);
            methodCount.put(methodName, methodCount.getOrDefault(methodName, 0) + 1);
            List<Integer> costList = methodCost.getOrDefault(methodName, new ArrayList<>());
            costList.add((int)costTime);
            methodCost.put(methodName, costList);
        }
        return obj;
    }

    //获得方法名
    public String getMethodName(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        return method.getName();
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder("MethodCount:\n");
        Map<String,Integer> sorted = sortByValue(methodCount);
        sorted.forEach(
                (method, count) -> {
                    sb.append("method=" + method + ", " + "count=" + count+'\n');
                }
        );
        sb.append('\n');
        sb.append("MethodCosts:\n");
        methodCost.forEach(
                (method, costList) -> {
                    IntSummaryStatistics stat = costList.stream().collect(Collectors.summarizingInt(x->x));
                    String info = String.format("method=%s, sum=%d, avg=%d, max=%d, min=%d, count=%d", method,
                            (int)stat.getSum(), (int)stat.getAverage(), stat.getMax(), stat.getMin(), (int)stat.getCount());
                    sb.append(info+'\n');
                }
        );

        sb.append('\n');
        sb.append("DetailOfMethodCosts:\n");
        methodCost.forEach(
                (method, costList) -> {
                    String info = String.format("method=%s, cost=%s", method, costList);
                    sb.append(info+'\n');
                }
        );
        return sb.toString();
    }
}
