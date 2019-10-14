package com.cxy.demo.demoasync.service;


import com.cxy.demo.demoasync.AsyncDemo;
import com.cxy.demo.demoasync.entity.GitHubUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@Slf4j(topic = "Logger")
public class FindGitHubUserRunner implements CommandLineRunner {

    @Autowired
    private AsyncDemo asyncDemo;


    @Override
    public void run(String... args) throws Exception {
        //计时
        long start = System.currentTimeMillis();
        if(asyncDemo.isServerAlive("https://api.github.com/users").get()){
            // 三次异步查找
            CompletableFuture<GitHubUser> page1 = asyncDemo.findGitHubUser("currynice");
            CompletableFuture<GitHubUser> page2 = asyncDemo.findGitHubUser("mojombo");
            CompletableFuture<GitHubUser> page3 = asyncDemo.findGitHubUser("defunkt");

            // 全部完成
            CompletableFuture.allOf(page1, page2, page3).join();

            // 结果
            log.info("Elapsed time: " + (System.currentTimeMillis() - start));
            log.info("--> " + page1.get());
            log.info("--> " + page2.get());
            log.info("--> " + page3.get());
        }


    }

}
