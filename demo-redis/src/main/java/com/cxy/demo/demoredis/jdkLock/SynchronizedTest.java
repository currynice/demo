package com.cxy.demo.demoredis.jdkLock;


/**
 * synchronized可以修饰方法和代码块,自动加锁解锁
 */
public class SynchronizedTest {

    //修饰no-static method,锁定当期类实例对象
    public synchronized void functionA(){
        System.out.println("iAmFunctionA");
        functionB();
    }
    public synchronized void functionB(){
        System.out.println("iAmFunctionB");
        functionA();
    }

    //修饰static method,锁定当前类Class对象
    public synchronized static void functionC(){
        System.out.println("iAmFunctionC");
    }
    Object a;
    void printA(){
        synchronized (a){
            System.out.println(a);
        }
    }

    //可重入锁的作用就是为了避免死锁
    public static void main(String args[]){
        SynchronizedTest s = new SynchronizedTest();
        s.functionA();
    }

}
