package com.cxy.demo.demoredis;

import io.rebloom.client.Client;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * jedis 2.9不支持拓展指令
 */
public class BloomTest {

    private static String chars;
    static {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            builder.append((char) ('a' + i));
        }
        chars = builder.toString();
    }


    private String randomString(int n) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int idx = ThreadLocalRandom.current().nextInt(chars.length());
            builder.append(chars.charAt(idx));
        }
        return builder.toString();
    }
    private List<String> randomUsers(int n) {
        List<String> users = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            users.add(randomString(64));
        }
        return users;
    }
    public static void main(String[] args) {
//        BloomTest bloomer = new BloomTest();
//        List<String> users = bloomer.randomUsers(100000);
//        List<String> usersTrain = users.subList(0, users.size() / 2);
//        List<String> usersTest = users.subList(users.size() / 2, users.size());
//
//        Client client = new Client(new JedisPool());
//        client.delete("codehole");
//
//        // 对应 bf.reserve 指令
//        client.createFilter("codehole", 50000, 0.001);
//        for (String user : usersTrain) {
//            client.add("codehole", user);
//        }
//        int falses = 0;
//        for (String user : usersTest) {
////            布隆过滤器对于已经见过的元素肯定不会误判，它只会误判那些没见过的元
////            素。使用 bf.exists 去查找没见过的元素
//            boolean ret = client.exists("codehole", user);
//            if (ret) {
//                falses++;
//            }
//        }
//        System.out.printf("%d %d\n", falses, usersTest.size());
//        client.close();
    }
}
//运行一下，等待约 1 分钟，输出如下：
//        total users 100000
//        all trained
//        6 50000
//        我们看到了误判率大约 0.012%，比预计的 0.1% 低很多，不过布隆的概率是有误差
//        的，只要不比预计误判率高太多，都是正常现象。

