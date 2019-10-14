package com.cxy.demo.demoasync.jdk5Future;

import java.util.concurrent.*;

/**
 * Future
 *
 * 提交可回调的task,  {@link ExecutorService#submit(Callable)}
 * ThreadPool 将真正的值返回给 Future
 */
public class FurtureTest {

    public static void main(String args[]) throws ExecutionException, InterruptedException {
        ExecutorService fixedService = Executors.newFixedThreadPool(4);
        Future<Integer> future = fixedService.submit(()->{return 10;});

        //get()是阻塞方法(阻塞调用线程,所以用限制时间)
        try {
            System.out.println(future.get(20L,TimeUnit.SECONDS));
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

}
