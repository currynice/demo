package com.cxy.demo.loading.exit;


import org.springframework.boot.ExitCodeGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


/**
 * 声明应用因自主退出而生成的exitCode
 */
@Component
public class ExitCodeConfig {

    /**
     * ExitCodeGenerator接口可以由异常实现。
     * 当遇到这样的异常时，Spring Boot返回由实现的getExitCode()方法提供的退出代码
     * @return
     */
    @Bean
    public ExitCodeGenerator exitCodeGenerator(){
        return ()->42;
    }


}
