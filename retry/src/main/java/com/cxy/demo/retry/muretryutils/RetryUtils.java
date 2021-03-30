package com.cxy.demo.retry.muretryutils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

/**
 * @author chengxinyu
 * @version 1.1.0
 * @description 异常重试工具类,因为Spring-retry 通过aspect增强的方式，所以，方法内调用是不工作的，因此自己实现一个简单的重试机制
 * @date 2020年07月15日9:58
 */
public class RetryUtils  {

    private static final Logger LOGGER = LoggerFactory.getLogger(RetryUtils.class);


    /**
     *  遇到异常<strong>立即重试</strong>
     * @param retryLimit 重试次数
     * @param retryCallable 重试回调
     */
    public static <T extends ResultCheck> T retryAtOnce(int retryLimit, Callable<T> retryCallable) {
        T result = null;
        for (int i = 0; i < retryLimit; i++) {
            try {
                result = retryCallable.call();
            } catch (Exception e) {
                LOGGER.warn("执行第" + (i + 1) + "次，出错",e);
                continue;
            }
            if (null != result && result.matching()) break;
            LOGGER.error("执行第" + (i + 1) + " 次，结果不符合预期");
        }
        return result;
    }

    /**
     * <strong>指数退避</strong> 地进行异常重试
     * 一般是 网络不稳定，连接失败, 所以不会立即重连，而是通过指数退避的方式，每隔 1 秒、2 秒、4 秒、8 秒，以 2 的幂次为间隔重新建立连接
     *
     * @param retryLimit 重试次数
     * @param retryCallable 重试回调
     * @throws InterruptedException 中断异常(需要调用方进行try-catch 捕获后处理)
     */
    public static <T extends ResultCheck> T retryBackOff(int retryLimit,Callable<T> retryCallable) throws InterruptedException {

        T result = null;
        for (int i = 0; i < retryLimit; i++) {

            // 第几次重连
            int order = (i+1);
            // 本次间隔
            int delay = 1 << order;

            try {
                result = retryCallable.call();
            } catch (Exception e) {
                LOGGER.warn("执行第" + (i + 1) + "次，出错",e);
                if(order!=retryLimit){//最后一次不等待了
                    Thread.sleep(delay*1000L);
                }
                continue;
            }
            System.out.println(result.toString());
            if (null != result && result.matching()) break;
            LOGGER.error("执行第" + (i + 1) + " 次，结果不符合预期");
            if(order!=retryLimit){
                Thread.sleep(delay*1000L);
            }
        }
        return result;
    }
}