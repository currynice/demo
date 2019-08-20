package com.cxy.demo.dynamicallyswitch.service;


import com.cxy.demo.dynamicallyswitch.DataSources;
import com.cxy.demo.dynamicallyswitch.RoutingDataSource;
import com.cxy.demo.dynamicallyswitch.dao.TestDao;
import com.cxy.demo.dynamicallyswitch.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@Slf4j(topic = "Logger")
public class TestServiceImpl implements TestService {
    @Autowired
    private TestDao testDao;

    @Override
    @RoutingDataSource(value = DataSources.MASTER_DB,operate = "来自主数据源操作")
    public List<User> findList1() {
        List<User> list = null;
        try{
            list =testDao.getAllUser1();
        }catch (Exception e){
            log.error("查询失败!原因是:",e);
        }
        return list;
    }


    @Override
    @RoutingDataSource(value = DataSources.SLAVE_DB,operate = "来自从数据源操作")
    public List<User> findList2() {
        List<User> list = null;
        try{
            list =testDao.getAllUser2();
        }catch (Exception e){
            log.error("查询失败!原因是:",e);
        }
        return list;
    }
}
