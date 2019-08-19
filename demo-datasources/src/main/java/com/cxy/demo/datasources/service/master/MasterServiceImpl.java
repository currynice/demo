package com.cxy.demo.datasources.service.master;

import com.cxy.demo.datasources.dao.MasterDao;
import com.cxy.demo.datasources.entity.User;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j(topic = "Logger")
public class MasterServiceImpl implements MasterService {
    @Autowired
    private MasterDao masterDao;

    @Override
    public List<User> findList() {
        List<User> list = null;
        try{
            Page<User> page = PageHelper.startPage(1,2);
            list =masterDao.getAllUser();
            log.info("一共"+page.getTotal()+"条数据,实际"+list.size()+"条数据");
        }catch (Exception e){
            log.error("查询失败!原因是:",e);
        }
        return list;
    }
}
