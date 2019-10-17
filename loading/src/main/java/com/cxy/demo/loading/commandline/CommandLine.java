package com.cxy.demo.loading.commandline;



import com.cxy.demo.loading.LoadingApplication;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import java.util.Arrays;


/**
 * <strong>时间过长的初始化可以考虑异步化</strong>
 *
 *
 * 命令行工具 args即主方法的参数 ,
 * @see LoadingApplication#main(String[]), 启动时设置Program arguments
 *
 * ApplicationRunner 的run 方法的参数是
 * @see ApplicationArguments 对象， 想从 中获取入口类中 main 方法接收的参数，调 ApplicationArguments 中的 getNonOptionArgs 方法即可。
 * ApplicationArguments中的 getOptionName 法用来取项目启动命令行中参数 key ，例如将本项目打成jar
 * 运行java- xxx.jar name=cxy 111 222 令来启动项目 此时 {@link ApplicationArguments#getOptionNames()}  方法获取到的就name{@link ApplicationArguments#getOptionValues(String)} ()} 则是获取相应的 value
 * 获取111 222 数组可以 {@link ApplicationArguments#getNonOptionArgs()}方法
 */
@Service
@Order(1)
public class CommandLine implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
       System.out.println(1+"  "+ Arrays.toString(args));
    }
}
