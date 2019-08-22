package com.cxy.demo.loading.commandline;



import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@Order(2)
public class CommandLine2 implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
       System.out.println(2);
    }
}
