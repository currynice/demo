package com.cxy.demo.demoredis;

import com.cxy.demo.demoredis.jdkLock.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo2Tests {

    @Autowired
    private RedisTemplate redisTemplate;


    @Qualifier("multiTemplate")
    @Autowired
    private RedisTemplate multiTemplate;

    @Autowired
    private ValueOperations valueOperations;

    private ReentrantLock lock= new ReentrantLock();

    //@Test
    public void contextLoads() throws InterruptedException {
        List<Thread> threadList =threadList(5);
        for(Thread t:threadList){
            t.start();
        }
        Thread.sleep(60*1000);

        for(Thread t:threadList){
           t.join();
        }
    }

    private  List<Thread> threadList(int count){
        List<Thread> threadList = new ArrayList<>();
        for(int i=0;i<count;i++){
            Task task = new Task(lock);
            Thread t = new Thread(task);
            threadList.add(t);
        }
        return threadList;
    }

    /**
     * 99553
     * 99.553%
     */
    @Test
    public void testHyperLoglog(){
        for(int i=1;i<=10*10000;i++){
            redisTemplate.opsForHyperLogLog().add("2019-09-30:index:uv",i);
            Long count = redisTemplate.opsForHyperLogLog().size("2019-09-30:index:uv");
            System.out.println();
        }
        System.out.println(redisTemplate.opsForHyperLogLog().size("2019-09-30:index:uv"));
        System.out.println((double)(redisTemplate.opsForHyperLogLog().size("2019-09-30:index:uv"))/1000+"%");
    }

    @Test
    public void testScan(){
        for(int i=1;i<=10*10000;i++){
            redisTemplate.opsForHyperLogLog().add("key"+i,i);

        }
    }

    @Test
    public void testWatch(){

            multiTemplate.opsForValue().set("account1","5");
            multiTemplate.watch("account1");
            int value = Integer.valueOf((String) multiTemplate.opsForValue().get("account1"));
            value *= 2; // 加倍
             multiTemplate.multi();
             multiTemplate.opsForValue().set("account1",value);
        if (multiTemplate.exec() != null) {//jedis事务失败返回null
             System.out.println(multiTemplate.opsForValue().get("account1"));
        }else {
            System.out.println("失败");
        }

    }

    @Test
    public void sss(){
            System.out.println(multiTemplate.opsForValue().get("account1"));

    }







}
