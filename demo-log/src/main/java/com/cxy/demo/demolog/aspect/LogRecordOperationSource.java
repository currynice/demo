package com.cxy.demo.demolog.aspect;

import com.cxy.demo.demolog.annotation.LogRecord;
import java.lang.reflect.Method;


/**
 * Description:  </br>
 * Date: 2021/9/18 9:36
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
public class LogRecordOperationSource {


    public LogRecordOp computeLogRecordOperations(Method method, Class<?> targetClass) {

        LogRecordOp result = new LogRecordOp();
        LogRecord logRecord =  method.getAnnotation(LogRecord.class);
        if(logRecord==null){
           return result;
        }
        result.setSuccess(logRecord.bizNo());
        result.setFail(logRecord.fail());
        result.setOperator(logRecord.operator());
        result.setBizNo(logRecord.bizNo());
        result.setCategory(logRecord.category());
        result.setDetail(logRecord.detail());
        result.setCondition(logRecord.condition());
        return result;

    }
}
