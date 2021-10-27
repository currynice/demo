package com.cxy.demo.demolog.aspect;

import lombok.Data;

/**
 * Description:   </br>
 * Date: 2021/9/18 9:42
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
@Data
public class LogRecordOp {

    //操作日志成功的文本模板（SpEL表达式），必填
    private String success;

    //操作日志失败的文本模板，非必填
    private  String fail = "";

    //操作日志执行人(操作日志很重要的信息)，非必填
    // 普通Web 应用中用户信息都是保存在一个线程上下文的静态方法中，
    // 假定获取当前登陆用户的方式是 UserContext.getCurrentUser()）。
    // 所以，默认 operator = "#{T(com.cxy.demo.demolog.UserContext).getCurrentUser()}"
    private String operator = "#{T(com.cxy.demo.demolog.UserContext).getCurrentUser()}";

    //操作日志绑定的业务标识(方便查询某个业务id的所有操作)，必填
    private String bizNo;

    //操作日志类型，非必填
    private String category = "";

    //拓展参数，记录操作日志的修改详情，非必填
    private String detail =  "";

    //记录条件，非必填
    private String condition = "";
}
