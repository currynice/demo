package com.cxy.demo.demolog.entity;

import lombok.Data;

/**
 * Description:   </br>
 * Date: 2021/9/17 16:00
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */

public class UpdateDelivery {

    //配送地址
    private String address;

    //配送单号
    private String deliveryOrderNo;


    //配送员id
    private String userId;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDeliveryOrderNo() {
        return deliveryOrderNo;
    }

    public void setDeliveryOrderNo(String deliveryOrderNo) {
        this.deliveryOrderNo = deliveryOrderNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
