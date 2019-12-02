package com.cxy.demo.demoquartz.tasks;

import com.cxy.demo.demoquartz.operation.SchedulerOperation;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;


public class GoodAddTask extends QuartzJobBean {

    private static Logger logger = LoggerFactory.getLogger(GoodAddTask.class);


    @Autowired
    private SchedulerOperation schedulerOperation;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        logger.info("商品添加完成后执⾏任务，任务时间:{}",new Date());
        try {
            schedulerOperation.createTime();
            logger.info("111");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
