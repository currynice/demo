package com.cxy.demo.retry.muretryutils;

/**
 * Description:  回调结果检查 <br>
 * Date: 2020/7/29 10:18  <br>
 * @author :cxy <br>
 * @version : 1.0 <br>
 */
public interface ResultCheck {

    //是否ok
    boolean matching();
}

