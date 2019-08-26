package com.cxy.demo.demojpa.entity;

import lombok.*;
import org.hibernate.annotations.Type;
import org.joda.money.Money;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Builder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_menu")
public class Coffee extends  BaseEntity implements Serializable {




    private String name;

    @Column
    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyAmount",parameters = {@ org.hibernate.annotations.Parameter(name="currencyCode",value="CNY")})
    private Money price;




}
