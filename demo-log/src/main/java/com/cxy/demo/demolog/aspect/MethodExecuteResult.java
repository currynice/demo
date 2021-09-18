package com.cxy.demo.demolog.aspect;

/**
 * Description:   </br>
 * Date: 2021/9/17 17:45
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
public class MethodExecuteResult {

    private boolean success;

    private Throwable throwable;

    private String errorMsg;


    public MethodExecuteResult(boolean success, Throwable throwable,String errorMsg) {
        this.success = success;
        this.throwable = throwable;
        this.errorMsg = errorMsg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
