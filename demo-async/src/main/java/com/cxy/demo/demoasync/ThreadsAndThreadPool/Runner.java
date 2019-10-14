package com.cxy.demo.demoasync.ThreadsAndThreadPool;

import java.util.Date;

public class Runner implements Runnable {
    @Override
    public void run() {
        Date date = new Date();
        System.out.println(Thread.currentThread().getName()+date.toString());
    }
}
