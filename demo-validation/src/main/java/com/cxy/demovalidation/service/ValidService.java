package com.cxy.demovalidation.service;


import com.cxy.demovalidation.bean.A;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ValidService {



    /**
     * 使用hibernate的注解,返回全部
     * @param failFast(false） 将完成校验,返回全部信息
     */
     private static final Validator validator = Validation
            .byProvider(HibernateValidator.class).configure().failFast(false).buildValidatorFactory().getValidator();


     public static <T>String validParam(T obj){
         Set<ConstraintViolation<T>> constraintViolations =  validator.validate(obj);
         if (!constraintViolations.isEmpty()){
             StringBuilder sb = new StringBuilder();
             Iterator<ConstraintViolation<T>> iterator = constraintViolations.iterator();
             while (iterator.hasNext()) {
                 ConstraintViolation<T> next = iterator.next();
                 sb.append(next.getMessage());
                 sb.append(";");
             }
             return sb.toString();
         }
         return null;
     }


    public   <T>String validParam(T obj,Class c){
        Set<ConstraintViolation<T>> constraintViolations =  validator.validate(obj,c);
        if (!constraintViolations.isEmpty()){
            StringBuilder sb = new StringBuilder();
            Iterator<ConstraintViolation<T>> iterator = constraintViolations.iterator();
            while (iterator.hasNext()) {
                ConstraintViolation<T> next = iterator.next();
                sb.append(next.getMessage());
                sb.append(";");
            }
            return sb.toString();
        }
        return null;
    }


    public static void main(String[] args){
        A a = new A();
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy/MM/dd" );

        String start = "2019/08/08";

        try {
            Date checkDate = sdf.parse(start);
            a.setTime(new Date());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(validParam(a));


    }



}
