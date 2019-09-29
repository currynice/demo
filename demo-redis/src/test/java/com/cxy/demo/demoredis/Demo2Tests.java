package com.cxy.demo.demoredis;

import com.cxy.demo.demoredis.jdkLock.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo2Tests {

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




}
