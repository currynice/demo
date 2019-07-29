package com.cxy.demovalidation.service;


import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.*;

@Service
public class ValidService {



    /**
     * 使用hibernate的注解,返回全部
     * @param failFast(false） 将完成校验,返回全部信息
     */
     private static final Validator validator = Validation
            .byProvider(HibernateValidator.class).configure().failFast(false).buildValidatorFactory().getValidator();


     public <T>String validParam(T obj){
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


    public <T>String validParam(T obj,Class c){
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




}
