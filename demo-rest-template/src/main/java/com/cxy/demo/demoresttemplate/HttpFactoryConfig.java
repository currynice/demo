package com.cxy.demo.demoresttemplate;


import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * 实现ClientHttpRequestFactory，默认SimpleClientHttpRequestFactory
 * 还有:
 * Apache HttpComponents:HttpComponentsClientHttpRequestFactory
 *
 * Netty: Netty4ClientHttpRequestFactory(过时 since Spring5，ReactiveWeb使用WebClient)
 *
 * OkHttp:安卓
 *
 */
@Configuration
public class HttpFactoryConfig {
    //http连接池管理 keepAlive PoolingHttpClientConnectionManager
    public HttpComponentsClientHttpRequestFactory requestFactory(){
        PoolingHttpClientConnectionManager connectionManager =
                new PoolingHttpClientConnectionManager(30, TimeUnit.SECONDS);//相当于ttl 30s

//
//        defaultMaxPerRoute=20，同一个主机 / 域名的最大并发请求数为 2。假设爬虫等需要 10 个并发，默认值太小限制了爬虫的效率。
//        maxTotal=20，所有主机整体最大并发为 20，即 HttpClient 整体的并发度。
//        使用同一个 HttpClient 访问 10 个域名，defaultMaxPerRoute 设置为 10，为确保每一个域名都能达到 10 并发，需要把 maxTotal 设置为 100。
        connectionManager.setMaxTotal(200);//200个连接
        connectionManager.setDefaultMaxPerRoute(20);//每个Route20个

        //避免无限制等待,请求超时设置，connectTimeout/readTimeout
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .disableAutomaticRetries()//关闭自动重试,比如支付等关键重试,结合下游请求幂等保证业务安全
                //设置自定义的请求超时逻辑
                //默认(DefaultConnectionKeepAliveStrategy.INSTANCE)
                .setKeepAliveStrategy(new CustomConnectionKeepAliveStrategy())
                .build();

        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }


    //通过配置RestTemplateCustomizer达到定制化
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){

        //return new  RestTemplate();
        return builder
                //连接超时100s
                .setConnectTimeout(Duration.ofMillis(100))
                //读取超时
                .setReadTimeout(Duration.ofMillis(500))
                .requestFactory(this::requestFactory)
                .build();
    }


    //todo SSL证书校验

}
