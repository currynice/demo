package com.cxy.demo.demomongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 如果不使用Spring Data Mongo，可以注入com.mongodb.MongoClient or com.mongodb.client.MongoClient 的Bean，而不是data-mongo的 MongoDbFactory。
 * 通过自定义的 MongoClient bean 或者 MongoDbFactory完全控制建立MongoDB连接
 *
 * data-mongodb-reactive  需要ssl
 */
@SpringBootApplication
public class DemoMongoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoMongoApplication.class, args);
    }

}
