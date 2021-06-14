package com.cxy.demo.demolog;


import org.slf4j.MDC;
import lombok.extern.slf4j.Slf4j;
import java.util.UUID;

/**
 * <h1>MDC</h1>
 * */
@Slf4j
public class UseMDC {

    // 使用 MDC 之前, 需要在配置文件中先配置 %X{REQUEST_ID}
    private static final String FLAG = "REQUEST_ID";

    // -----------------------------------------------------------------------------------------------------------------
    // 第一个例子

    private static void mdc01() {

        MDC.put(FLAG, UUID.randomUUID().toString());
        log.info("log in mdc01");
        mdc02();

        log.info("MDC FLAG is: [{}]", MDC.get(FLAG));
        MDC.remove(FLAG);
        log.info("after remove MDC FLAG");

        // clear：清除所有的 key
    }

    private static void mdc02() {

        log.info("log in mdc02");
    }

    // -----------------------------------------------------------------------------------------------------------------
    // 多线程下的mdc使用
    static class MyHandler extends Thread {

        private final String name;

        public MyHandler(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            MDC.put(FLAG, UUID.randomUUID().toString());
            log.info("start to process: [{}]", this.name);

            try {
                Thread.sleep(1200);
            } catch (InterruptedException e) {
                log.info(e.getMessage());
            }

            log.info("done to process: [{}]", this.name);
            MDC.remove(FLAG);
        }
    }

    private void MultiThreadUseMdc() {

        new MyHandler("thread1").start();
        new MyHandler("thread2").start();
    }


    public static void main(String[] args) {

        mdc01();
//        new UseMDC().MultiThreadUseMdc();
    }
}
