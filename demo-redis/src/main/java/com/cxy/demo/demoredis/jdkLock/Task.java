package com.cxy.demo.demoredis.jdkLock;

import java.util.concurrent.locks.ReentrantLock;
public class Task implements Runnable{



    private ReentrantLock lock;

    public Task(ReentrantLock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true){
            try {
                doLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void doLock() throws InterruptedException {
            boolean goOn = lock.tryLock();
            String name = Thread.currentThread().getName();
            System.out.println(goOn?name+"准备执行":"正在执行,本次忽略");
            if(goOn){
                try{
                    System.out.println(name+"执行一次");
                }finally {

                        lock.unlock();

                }
            }
        }
    }


