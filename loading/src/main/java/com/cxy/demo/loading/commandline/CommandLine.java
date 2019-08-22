package com.cxy.demo.loading.commandline;



import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;


/**
 * 命令行工具
 */
@Service
@Order(1)
public class CommandLine implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
       System.out.println(1);
    }
}
