package com.cxy.demo.demoresttemplate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;


@Slf4j
public class TestController {

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
        //sleep 1s
        Thread.sleep(1000L);
        return CompletableFuture.completedFuture(results);
    }
}
