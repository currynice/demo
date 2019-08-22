package com.cxy.demo.loading.exit;


import com.cxy.demo.loading.exceptions.DemoException;
import org.springframework.boot.ExitCodeExceptionMapper;
import org.springframework.stereotype.Component;

/**
 * 异常与程序退出的映射
 * 声明应用因异常退出而生成的exitCode
 * 遇到异常将退出,0的话不会应用退出(直接System.exit太粗暴了,主线程执行了该逻辑，所有)
 * 多线程情况:https://yq.aliyun.com/articles/555286?utm_content=m_1000007484
 */
@Component
public class DemoExitCodeExceptionMapper implements ExitCodeExceptionMapper {
    @Override
    public int getExitCode(Throwable exception) {
        if(exception instanceof DemoException){
            return -5;
        }
        return 0;
    }
}
