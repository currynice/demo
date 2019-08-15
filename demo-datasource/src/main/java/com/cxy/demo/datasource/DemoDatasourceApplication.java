package com.cxy.demo.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
@Slf4j(topic = "Logger")
public class DemoDatasourceApplication implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {

        SpringApplication.run(DemoDatasourceApplication.class, args);
    }

    /**
     * 查看数据源以及连接信息
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        showConnection();
        showData();
    }

    /**
     * 查看数据源记忆连接信息
     * @throws SQLException
     */
   public void showConnection() throws SQLException {
       log.info(dataSource.toString());
       Connection connection = dataSource.getConnection();
       log.info(connection.toString());
    }

    public void showData(){
       jdbcTemplate.queryForList("SELECT * FROM FOO").forEach(row -> log.info(row.toString()));
    }
}
