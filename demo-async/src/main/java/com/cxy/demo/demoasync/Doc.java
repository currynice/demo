package com.cxy.demo.demoasync;

import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @Author: cxy
 * @Date: 2019/10/13 15:40
 * @Description:
 */
public class Doc {
    /**
     * Thread
     * when the main thread dies, all its child thread dies without completing their execution
     * creating a thread is already expensive(https://stackoverflow.com/questions/5483047/why-is-creating-a-thread-said-to-be-expensive)
     */


    /**
     * ThreadPool
     *      is a pool with a fixed number of threads
     */



    /**
     * Spring {@link TaskExecutor} 本质是{@link java.util.concurrent.Executor}
     * spring集成了一些实现
     * {@link  SimpleAsyncTaskExecutor} 不是真的线程池，不重用线程，每次调用都会创建一个新的线程。
     * {@link  SyncTaskExecutor}没有实现异步调用，只是一个同步操作。适用于不需要多线程的地方
     * {@link  ConcurrentTaskExecutor} ：Executor的适配类，不推荐使用。如果ThreadPoolTaskExecutor不满足要求时，才用考虑使用这个类
     * {@link  SimpleThreadPoolTaskExecutor}：是Quartz的SimpleThreadPool的类。线程池同时被quartz和非quartz使用，才需要使用此类
     * {@link  ThreadPoolTaskExecutor} 推荐。 其实质是对java.util.concurrent.ThreadPoolExecutor的包装
     *
     */


    /**
     * @Async  异步任务
     *
     * 异步的方法有3种
     * 1. 最简单的异步调用，返回值为void
     * 2. 带参数的异步调用 异步方法可以传入参数
     * 3. 异常调用返回Future, 或  CompleteFuture(prefer to)
     */

}
