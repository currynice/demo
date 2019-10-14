package com.cxy.demo.demoasync;

import com.cxy.demo.demoasync.entity.GitHubUser;
import com.cxy.demo.demoasync.service.TestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Author: cxy
 * @Date: 2019/10/13 16:17
 * @Description:
 */
@Component
@Slf4j(topic = "Logger")
@Async
public class AsyncDemo {
    /**
     * void 方法
     */
    public void asyncInvokeSimplest() {
        log.info("asyncSimplest");
    }

    /**
     * 带参数的异步调用
     * 返回值是void，异常AsyncUncaughtException会被AsyncUncaughtExceptionHandler处理掉
     * @param s
     */
    public void asyncInvokeWithParameter(String... s) {
        log.info("asyncInvokeWithParameter, parementer={}", s);
        throw new TestException("asyncInvokeWithParameter error");
    }

    /**
     * 异步回调 返回Future
     * {
     *     {@link Future#isDone()} 异步任务是否完成
     *
     *     {@link Future#cancel(boolean)}
     *     1.如果任务还没执行，那么如果想取消任务，就一定返回true，与参数无关。
     *     2.如果任务已经执行完成，那么任务一定是不能取消的，所以此时返回值都是false，与参数无关。
     *     3.如果任务正在执行中，那么此时是否取消任务就看参数是否允许打断（true/false）。
     *
     *     {@link Future#isCancelled()} 是否取消成功
     *
     *     {@link Future#get()} 取得value
     *
     *      {@link Future#get(long, TimeUnit)} 设置超时时间，get()为阻塞方法,超时未完成抛出 TimeoutException
     *
     * }
     * @param i
     * 返回值是Future，不会被AsyncUncaughtExceptionHandler处理，需要我们在方法中捕获异常并处理
     * 或者在调用方在调用Futrue.get时捕获异常进行处理
     * @return
     */
//    @Async
    public Future<String> asyncInvokeReturnFuture(int i) {
        log.info("asyncInvokeReturnFuture, parementer={}", i);
        Future<String> future;
        try {
            Thread.sleep(1000 * 1);
            future = new AsyncResult<>("success:" + i);
            throw new TestException("asyncInvokeReturnFuture error");
        } catch (InterruptedException e) {
            future = new AsyncResult<>("error-InterruptedException");
        }catch (TestException e1){
            future = new AsyncResult<>("error-TestException");
        }
        return future;
    }


    //CompleteFuture   与Future不一样，异步方法必须执行完todo
    private List<String> movies =
            new ArrayList<>(
                    Arrays.asList(
                            "Forrest Gump",
                            "Titanic",
                            "Spirited Away",
                            "The Shawshank Redemption",
                            "Zootopia",
                            "Farewell ",
                            "Joker",
                            "Crawl"));


    /**
     * 返回x开头的电影
     * @param x
     * @return
     */
    public CompletableFuture<List<String>> getMoviesStartWithX(String x){
        // 打印日志
        log.warn(Thread.currentThread().getName() + "start this task!");

        return CompletableFuture.completedFuture(movies.stream().filter(m->m.startsWith(x)).collect(Collectors.toList()));

    }

    public CompletableFuture<Boolean> isServerAlive(String host) {
        CompletableFuture<Boolean> future = new CompletableFuture<Boolean>(){
            @Override
            public Boolean get() throws InterruptedException, ExecutionException {
                InetAddress address = null;
                try {
                    address = InetAddress.getByName(host);
                    return address.isReachable(1000);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                    return false;
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        };
        return future;
    }



    @Autowired
    private RestTemplate restTemplate;

    /**
     * 以currynice 进行测试
     * @param user
     * @return
     */
    public CompletableFuture<GitHubUser> findGitHubUser(String user) throws InterruptedException {
        log.info("Looking up " + user);
        String url = String.format("https://api.github.com/users/%s", user);
        //invoke https://api.github.com/users convert to GitHubUser
        GitHubUser results = restTemplate.getForObject(url, GitHubUser.class);
        //sleep 1s ,用于演示这么调用的好处
        Thread.sleep(1000L);
        return CompletableFuture.completedFuture(results);
    }






}
