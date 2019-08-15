package com.cxy.demo.datasources;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootApplication
@Slf4j(topic = "Logger")
public class DemoDatasourcesApplication implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;

    public static void main(String[] args) {

        SpringApplication.run(DemoDatasourcesApplication.class, args);
    }

    /**
     * 查看数据源以及连接信息
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        log.info(dataSource.toString());
        Connection connection = dataSource.getConnection();
        log.info(connection.toString());
    }
}
