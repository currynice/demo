package com.cxy.demo.demoresttemplate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.concurrent.CompletableFuture;


@Slf4j
@RestController
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 以currynice 进行测试,http://localhost:8080/findGitHubUser?userName=currynice
     * @param userName
     * @return
     */
    @RequestMapping("/findGitHubUser")
    public CompletableFuture<GitHubUser> findGitHubUser(String userName) throws InterruptedException {
        log.info("Looking up " + userName);
        String url = String.format("https://api.github.com/users/%s", userName);


        URI uri = UriComponentsBuilder.fromUriString("https://api.github.com/users/{userName}").build(userName);

        //invoke https://api.github.com/users convert to GitHubUser,also can  convert to ResponseEntity
        GitHubUser results = restTemplate.getForObject(url, GitHubUser.class);
        //sleep 1s
        Thread.sleep(1000L);
        return CompletableFuture.completedFuture(results);
    }



    /**
     * 发送请求时带上RequestEntity todo
     * @return
     */
    @RequestMapping("/testForRequestEntity")
    public ResponseEntity testForRequestEntity() {

        String urlTemplate = "http://example.com/hotels/{number}";
        URI uri = UriComponentsBuilder.fromUriString(urlTemplate).build(402);

        //GET请求
        RequestEntity<Void> requestEntity = RequestEntity.get(uri).header("testHeader","value").build();

        //结果确定是String
        ResponseEntity<String> response = restTemplate.exchange(requestEntity,String.class);

        //结果是集合,解析泛型对象
        ParameterizedTypeReference<String> ptr = new ParameterizedTypeReference<String>(){};
        ResponseEntity<String> responseList = restTemplate.exchange(uri, HttpMethod.GET,null,ptr);

        String responseHeader = response.getHeaders().getFirst("testHeader");
        String body = response.getBody();

        return response;
    }
}
