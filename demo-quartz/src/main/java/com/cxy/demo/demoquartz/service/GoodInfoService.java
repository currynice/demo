package com.cxy.demo.demoquartz.service;


import com.cxy.demo.demoquartz.dao.GoodInfoDao;
import com.cxy.demo.demoquartz.entity.GoodInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class GoodInfoService {
    @Autowired
    private GoodInfoDao goodInfoDao;



    //保存后提醒
    public Long save(GoodInfo goodInfo)throws Exception{
        goodInfoDao.save(goodInfo);
        return goodInfo.getId();
    }
}
