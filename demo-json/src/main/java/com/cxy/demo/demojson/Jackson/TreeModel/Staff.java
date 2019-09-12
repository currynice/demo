package com.cxy.demo.demojson.Jackson.TreeModel;

import lombok.Builder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 员工信息
 */
@Builder
public class Staff implements Serializable {


    private static final long serialVersionUID = 4059184504213106155L;

    private Name name;
    private int age;
    private List<Contact> contacts;              //  Array


    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
