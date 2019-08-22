package com.cxy.demo.loading.initBean;

import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 搞一个线程执行存储过程,出现异常通知管理员通知
 */
@Service
public class MyThread extends Thread {
    @Override
    public void run() {
        while(true)
        {
            try {
                System.out.println(new Date());
                //所要进行的操作，我这里就是调用存储过程了。

                //
                MyThread.sleep(3000);//休眠3秒，避免执行过于频繁

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
