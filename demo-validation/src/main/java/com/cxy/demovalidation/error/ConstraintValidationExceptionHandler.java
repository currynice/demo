package com.cxy.demovalidation.error;

import cn.hutool.core.collection.CollUtil;
import com.cxy.demovalidation.bean.DemoResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import java.util.Set;


@ControllerAdvice
public class ConstraintValidationExceptionHandler  {
    //改下errorCode
//    @ExceptionHandler(ConstraintViolationException.class)
//    public void constraintViolationException(HttpServletResponse response) throws IOException {
//        response.sendError(HttpStatus.BAD_REQUEST.value());//400
//    }




    /**
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public DemoResult resolveConstraintViolationException(ConstraintViolationException ex){
        DemoResult errorResult = new DemoResult(111);
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        if(!CollUtil.isEmpty(constraintViolations)){
            StringBuilder msgBuilder = new StringBuilder();
            for(ConstraintViolation constraintViolation :constraintViolations){
                msgBuilder.append(constraintViolation.getMessage()).append(",");
            }
            String errorMessage = msgBuilder.toString();
            if(errorMessage.length()>1){
                errorMessage = errorMessage.substring(0,errorMessage.length()-1);
            }
            errorResult.setMsg(errorMessage);
            return errorResult;
        }
        errorResult.setMsg(ex.getMessage());
        return errorResult;
    }
}
