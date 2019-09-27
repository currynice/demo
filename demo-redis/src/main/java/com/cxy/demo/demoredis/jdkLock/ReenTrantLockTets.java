//package com.cxy.demo.demoredis.jdkLock;
//
//import java.util.concurrent.locks.ReentrantLock;
//
///**
// * ReenTrantLock独有的能力：
// *
// * 1.ReenTrantLock可以指定是公平锁还是非公平锁。而synchronized只能是非公平锁。所谓的公平锁就是先等待的线程先获得锁。
// *
// * 2.ReenTrantLock提供了一个Condition（条件）类，用来实现分组唤醒需要唤醒的线程们，而不是像synchronized要么随机唤醒一个线程要么唤醒全部线程。
// *
// * 3.ReenTrantLock提供了一种能够中断等待锁的线程的机制，通过lock.lockInterruptibly()来实现这个机制。
// *
// */
//public class ReenTrantLockTets {
//
//    private ReentrantLock lock = new ReentrantLock();
//
//    /**
//     * 防止重复执行
//     */
//    public void avoidRepeat(){
//        boolean goOn = lock.tryLock();
//        String name = Thread.currentThread().getName();
//        System.out.println(goOn?name+"准备执行":name+"正在执行,本次忽略");
//         if(goOn){
//           try{
//             Thread.sleep(10000);
//         } catch (InterruptedException e) {
//               e.printStackTrace();
//           } finally {
//               lock.unlock();
//           }
//           }
//    }
//
//}
