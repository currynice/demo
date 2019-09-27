package com.cxy.demo.demoredis.jdkLock;

public class SynchronizedTest {
    public synchronized void functionA(){
        System.out.println("iAmFunctionA");
        functionB();
    }
    public synchronized void functionB(){
        System.out.println("iAmFunctionB");
        functionA();
    }
    //可重入锁的作用就是为了避免死锁
    public static void main(String args[]){
        SynchronizedTest s = new SynchronizedTest();
        s.functionA();
    }

}
