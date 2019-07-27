package com.cxy.demovalidation.annotations;


import cn.hutool.core.util.IdcardUtil;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 自定义 身份证号码注解
 */
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IdCard.Validator.class)
public @interface IdCard {

    String message() default "身份证号码不正确";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };



    class Validator implements ConstraintValidator<IdCard, String> {

        //注解下字段是否有效
        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {

            if (value == null || "".equals(value)) {
                return Boolean.TRUE;
            }
            return IdcardUtil.isValidCard(value);
        }
    }
}
