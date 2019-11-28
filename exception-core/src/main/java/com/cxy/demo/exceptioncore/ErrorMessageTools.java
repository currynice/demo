package com.cxy.demo.exceptioncore;


import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

/**
 * 格式化异常描述工具类
 */
public class ErrorMessageTools {

    private ErrorMessageTools() {
    }

    public static String getErrorMessage(String errCode,String ...params){
        LogicExceptionMessage logicExceptionMessage = SpringBeansTools.getBean(LogicExceptionMessage.class);
        Assert.notNull(logicExceptionMessage,"信息获取失败:请配置实现LogicExceptionMessage接⼝并设置实现类被Sp ringIoc所管理");
        //获取错误消息内容
        String	errMsg	=	logicExceptionMessage.getMessage(errCode);
        // 格式化错误消息内容
        return	ObjectUtils.isEmpty(params)?errMsg :String.format(errMsg,params);
    }
}
