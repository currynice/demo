package com.cxy.demo.loading;


import com.cxy.demo.loading.exceptions.DemoException;
import com.cxy.demo.loading.exceptions.NormalException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

/**
 * 主动调用SpringApplication.exit 方法使用ExitCodeGenerator ,可以通过 Bean注册，也可通过传值。
 * 应用异常退出使用 ExitCodeExceptionMapper, 只能通过 Bean 注册使用。
 * 取值：
 * 前后正负不同，取最新, 相同，取绝对值大的
 */
@SpringBootApplication
public class LoadingApplication {

    public static void main(String[] args) {

        SpringApplication.run(LoadingApplication.class, args);
        //打印出exitCODE (默认-1)  //todo exitCode 异常处理
        //System.exit(SpringApplication.exit( SpringApplication.run(LoadingApplication.class, args)));
    }



    @Bean
    @Order(4)
    public InitializingBean initBean() {
        return  ()-> System.out.println("init-bean");

    }


    @Bean
    @Order(3)
    public CommandLineRunner commandLine3() {
        return  args-> System.out.println(3);

        }


    @Bean
    @Order(4)
    public CommandLineRunner commandLine4() {
        return  args-> {
            System.out.println(4);
            throw new DemoException("DemoException");
//            throw new NormalException("NormalException");

        };

    }

}
