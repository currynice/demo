package com.cxy.demo.democors2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@RequestMapping("/cors")
public class DemoCors2Application {

    public static void main(String[] args) {
        SpringApplication.run(DemoCors2Application.class, args);
    }



}
