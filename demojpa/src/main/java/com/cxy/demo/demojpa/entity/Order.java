package com.cxy.demo.demojpa.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Builder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_order")
public class Order extends BaseEntity implements Serializable {


    private static final long serialVersionUID = -6667071489128244872L;
    private String customer;

    @ManyToMany
    @JoinTable(name = "t_order_coffee")
    private List<Coffee> items;

    @Enumerated
    @Column(nullable = false)
    private OrderState state;


}
