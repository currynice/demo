package com.cxy.demo.demoasync.ThreadsAndThreadPool;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class jdkThreadsAndThreadPools {
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    private static int dex = 0;

    //Thread
    private static void testThread(){
        Thread thread = new Thread(()->{
            while(true){
                System.out.println( atomicInteger.incrementAndGet());
            }
        });

        thread.start();
    }

    //Thread POOL


    /**
     * 创建一个单线程，适合顺序执行，如果中途出错了,会重新创建一个thread
     */
    private static void singleTest(){
        //Fixed sum of pools: 4
        ExecutorService service = Executors.newSingleThreadExecutor();
        //提交任务
        service.execute(new Runner());

        service.shutdown();
    }


    private static void fixedTest(){
        //Fixed sum of pools: 4
        ExecutorService fixedService = Executors.newFixedThreadPool(4);
        //提交任务
        fixedService.execute(new Runner());
        fixedService.execute(new Runner());
        fixedService.execute(new Runner());
        fixedService.execute(new Runner());
        fixedService.execute(new Runner());
        fixedService.shutdown();

    }

    private static void cachedTest(){
        //大小不固定，找不到可用的free thread，会创建新的
        ExecutorService cachedService = Executors.newCachedThreadPool();
        //提交任务
        cachedService.execute(new Runner());
        cachedService.execute(new Runner());
        cachedService.execute(new Runner());
        cachedService.execute(new Runner());
        cachedService.execute(new Runner());
        cachedService.shutdown();
    }

    //控制定时执行,别sleep了
    private static void scheduledTest(){
        ScheduledExecutorService scheduledService = Executors.newScheduledThreadPool(5);
        Date date = new Date();
        System.out.println(date.toString());
        //5s后间隔3s重复执行   上一次结束至下一次开始
        scheduledService.scheduleWithFixedDelay(new Runner(),5L,3L, TimeUnit.SECONDS);

        //run once after 2s
      //  scheduledService.schedule(new Runner(),2,TimeUnit.SECONDS);

        //开始间隔(successive executions)      上一次开始至下一次开始
        scheduledService.scheduleAtFixedRate(new Runner(),5L,3L, TimeUnit.SECONDS);
        scheduledService.shutdown();
    }

    //ThreadPool end



    //example


    public static void main(String args[]){

    }
}
