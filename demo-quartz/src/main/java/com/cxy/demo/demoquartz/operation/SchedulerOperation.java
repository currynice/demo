package com.cxy.demo.demoquartz.operation;

import com.cxy.demo.demoquartz.tasks.GoodAddTask;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;


/**
 * 使用的是SpringBoot配置的数据源，独立的话：用@QuartzDataSource作用于一个数据源bean,确保SchedulerFactoryBean和模式初始化都使用特定于quartz的数据源。
 */
@Component
public class SchedulerOperation {

    //注入任务调度器
    @Autowired
    private Scheduler scheduler;



    /**
     *	构建创建商品定时任务
     */
    public	void  createTime()	throws	Exception{
        //设置开始时间为1分钟后
        long  startAtTime	= System.currentTimeMillis() +	1000*60;
        //任务名称
        String	name = UUID.randomUUID().toString();
        //任务所属分组
        String	group	=	GoodAddTask.class.getName();
        //创建任务
        JobDetail jobDetail	=	JobBuilder.newJob(GoodAddTask.class).withIdentity(name,group).build();
        //创建任务触发器
        Trigger trigger	=	TriggerBuilder.newTrigger().withIdentity(name,group).withSchedule(SimpleScheduleBuilder.repeatSecondlyForTotalCount(20)).startAt(new Date(startAtTime)).build();
        //将触发器与任务绑定到调度器内
        scheduler.scheduleJob(jobDetail,trigger);

        }

    }
