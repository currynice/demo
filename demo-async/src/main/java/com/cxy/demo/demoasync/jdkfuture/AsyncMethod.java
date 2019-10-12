package com.cxy.demo.demoasync.jdkfuture;

/**
 * 几个将要被调用的异步方法
 */
public class AsyncMethod {

    //抛出异常的方法
    public void methodWithError(){
        throw new IllegalArgumentException("参数不对");
    }

    public void methodNormal(){
        System.out.println("everything is ok");
    }


    public void methodWithParms(String str){
        System.out.println(str);
    }


    public String  methodWithReturn(String str){
       return str;
    }
}
