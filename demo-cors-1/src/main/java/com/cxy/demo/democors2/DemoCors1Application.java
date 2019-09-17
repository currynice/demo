package com.cxy.demo.democors2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@RequestMapping("/cors")
public class DemoCors1Application {

    public static void main(String[] args) {
        SpringApplication.run(DemoCors1Application.class, args);
    }

    /**
     *
     * @param name
     * @return
     */
    @PostMapping("/add")
    public String addBook(String name){
        return name;
    }


    @DeleteMapping("/delete/{id}")
    public String deleteBookById(@PathVariable("id") String id){
        return id;
    }

}
