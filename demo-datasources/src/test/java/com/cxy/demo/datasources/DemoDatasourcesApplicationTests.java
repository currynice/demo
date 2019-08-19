package com.cxy.demo.datasources;

import com.cxy.demo.datasources.dao.MasterDao;
import com.cxy.demo.datasources.dao2.SlaveDao;
import com.cxy.demo.datasources.service.master.MasterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoDatasourcesApplicationTests {
    @Autowired
    private MasterDao masterDao;

    @Autowired
    private SlaveDao slaveDao;

    @Autowired
    private MasterService masterService;

    @Test
    public void contextLoads() {
//        masterDao.getAllUser().forEach(System.out::println);
//        System.out.println("<br>");
//        slaveDao.getAllUser().forEach(System.out::println);
        masterService.findList().forEach(System.out::println);
    }

}
