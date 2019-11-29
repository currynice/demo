package com.cxy.demo.demoquartz.entity;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class GoodInfo {
    /**
     * 商品编号
     */
    private	Long	id;
    /**
     * 商品名称
     */
    private	String	name;
    /**
     *	商品单位
     */
    private	String	unit;
    /**
     * 商品单价
     */
    private BigDecimal price;
}
