//package com.cxy.demo.demoquartz.operation;
//
//
//import org.quartz.*;
//import org.quartz.impl.matchers.GroupMatcher;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//import java.util.Map;
//import java.util.Map.Entry;
//import java.util.Set;
//import static org.quartz.JobBuilder.newJob;
//import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
//import static org.quartz.TriggerBuilder.newTrigger;
//
///**
// *
// * @description 定时任务操作
// * @version 1.1.0
// * @date 2019年6月1日下午4:15:05
// */
//@Component(value = "quartzManager")
//public class QuartzManager {
//	/**
//	 * 注入任务调度器
//	 */
//	@Autowired
//	private Scheduler scheduler;
//
//
//
//
//	/**
//	 *
//	 * @description 添加一个定时任务，使用默认的任务组名，触发器名，触发器组名
//	 * @param jobName 任务名
//	 * @param cls     任务
//	 * @param time    时间设置
//	 * @throws Exception
//	 */
//	public void addJob(String jobName, Class<? extends Job> cls, String time) throws Exception {
//		try {
//			// 用于描叙Job实现类及其他的一些静态信息，构建一个作业实例
//			JobDetail jobDetail = newJob(cls).withIdentity(jobName, JOB_GROUP_NAME).build();
//			// 创建一个新的TriggerBuilder来规范一个触发器
//			CronTrigger trigger = newTrigger()
//					// 给触发器起一个名字和组名
//					.withIdentity(jobName, TRIGGER_GROUP_NAME).withSchedule(CronScheduleBuilder.cronSchedule(time))
//					.build();
//			scheduler.scheduleJob(jobDetail, trigger);
//			if (!scheduler.isShutdown()) {
//				// 启动
//				scheduler.start();
//			}
//		} catch (Exception e) {
//			throw new ExceptionPack("添加一个定时任务失败", e, ExceptionGradeEnum.WARN);
//		}
//	}
//
//	/**
//	 * 添加一个定时任务，使用默认的任务组名，触发器名，触发器组名 （带参数）
//	 *
//	 * @param jobName 任务名
//	 * @param cls     任务
//	 * @param time    时间设置，参考quartz说明文档
//	 * @throws ExceptionPack
//	 */
//	public void addJob(String jobName, Class<? extends Job> cls, String time, Map<String, Object> parameter)
//			throws Exception {
//		try {
//			// 用于描叙Job实现类及其他的一些静态信息，构建一个作业实例
//			JobDetail jobDetail = newJob(cls).withIdentity(jobName, JOB_GROUP_NAME).build();
//			// 传参数
//			for (Entry<String, Object> entry : parameter.entrySet()) {
//				jobDetail.getJobDataMap().put(entry.getKey(), entry.getValue());
//			}
//			// 创建一个新的TriggerBuilder来规范一个触发器
//			CronTrigger trigger = newTrigger()
//					// 给触发器起一个名字和组名
//					.withIdentity(jobName, TRIGGER_GROUP_NAME).withSchedule(CronScheduleBuilder.cronSchedule(time))
//					.build();
//			scheduler.scheduleJob(jobDetail, trigger);
//			if (!scheduler.isShutdown()) {
//				// 启动
//				scheduler.start();
//			}
//		} catch (Exception e) {
//			throw new ExceptionPack("添加一个定时任务失败", e, ExceptionGradeEnum.WARN);
//		}
//	}
//
//	/**
//	 * 添加一个定时任务
//	 *
//	 * @param jobName          任务名
//	 * @param jobGroupName     任务组名
//	 * @param triggerName      触发器名
//	 * @param triggerGroupName 触发器组名
//	 * @param jobClass         任务
//	 * @param time             时间设置，参考quartz说明文档
//	 * @throws ExceptionPack
//	 */
//	public void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName,
//                       Class<? extends Job> jobClass, String time) throws Exception{
//		try {
//			// 任务名，任务组，任务执行类
//			JobDetail jobDetail = newJob(jobClass).withIdentity(jobName, jobGroupName).build();
//			// 触发器
//			CronTrigger trigger = newTrigger().withIdentity(triggerName, triggerGroupName)
//					.withSchedule(CronScheduleBuilder.cronSchedule(time)).build();
//			scheduler.scheduleJob(jobDetail, trigger);
//			if (!scheduler.isShutdown()) {
//				// 启动
//				scheduler.start();
//			}
//		} catch (Exception e) {
//			throw new ExceptionPack("添加一个定时任务失败", e, ExceptionGradeEnum.WARN);
//		}
//	}
//
//	/**
//	 * 添加一个定时任务 CronTrigger（带参数）
//	 *
//	 * @param jobName          任务名
//	 * @param jobGroupName     任务组名
//	 * @param triggerName      触发器名
//	 * @param triggerGroupName 触发器组名
//	 * @param jobClass         任务
//	 * @param time             时间设置
//	 * @param jobName    任务名,触发器名
//	 * @param parameter  任务传递参数
//	 */
//	public void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName,
//                       Class<? extends Job> jobClass, String time, Date startTime, Map<String, Object> parameter) {
//		try {
//			// 任务名，任务组，任务执行类
//			JobDetail jobDetail = newJob(jobClass).withIdentity(jobName, jobGroupName).build();
//			// 传参数
//			for (Entry<String, Object> entry : parameter.entrySet()) {
//				jobDetail.getJobDataMap().put(entry.getKey(), entry.getValue());
//			}
//			// 触发器
//			CronTrigger trigger = newTrigger().withIdentity(triggerName, triggerGroupName).startAt(startTime)
//					.withSchedule(CronScheduleBuilder.cronSchedule(time)).build();
//			scheduler.scheduleJob(jobDetail, trigger);
//			if (!scheduler.isShutdown()) {
//				// 启动
//				scheduler.start();
//			}
//		} catch (SchedulerException e){
//			throw new QuartzJobException(StrUtil.format("添加{}出错",jobName),e);
//		}
//	}
//
//
//
//
//
//	/**
//	 * <strong>添加 定时任务 SimpleTrigger  </strong>
//	 *
//	 *
//	 * @param jobName          任务名
//	 * @param jobGroupName     任务组名
//	 * @param triggerName      触发器名
//	 * @param triggerGroupName 触发器组名
//	 * @param jobClass       任务
//	 * @param startTime 开始时间 为 检查日期的 前3天，执行一次
//	 *@param parameter  任务参数
//	 */
//	public void addSimpleJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName,
//                             Class<? extends Job> jobClass, Date startTime, Map<String, Object> parameter) {
//		try {
//			// 任务名，任务组，任务执行类
//			JobDetail jobDetail =newJob(jobClass).withIdentity(jobName,jobGroupName).build();
//			// 传参数
//			for (Entry<String, Object> entry : parameter.entrySet()) {
//				jobDetail.getJobDataMap().put(entry.getKey(), entry.getValue());
//			}
//
//			// 触发器  执行一次
//			SimpleTrigger trigger = newTrigger().withIdentity(triggerName,triggerGroupName).startAt(startTime)
//					.withSchedule(simpleSchedule()
//							.withRepeatCount(0)).build();
//
//			//	trigger.
//			scheduler.scheduleJob(jobDetail, trigger);
//			if (!scheduler.isShutdown()) {
//				// 启动
//				scheduler.start();
//			}
//		} catch (SchedulerException e){
//			throw new QuartzJobException(StrUtil.format("添加{}出错",jobName),e);
//		}
//	}
//
//
//	/**
//	 * 修改一个任务的触发时间(使用默认的任务组名，触发器名，触发器组名)
//	 *
//	 * @param jobName 任务名
//	 * @param time    新的时间设置
//	 * @throws ExceptionPack
//	 */
//	public void modifyJobTime(String jobName, String time) throws Exception {
//		try {
//			// 通过触发器名和组名获取TriggerKey
//			TriggerKey triggerKey = TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME);
//			// 通过TriggerKey获取CronTrigger
//			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
//			if (trigger == null) {
//				return;
//			}
//			String oldTime = trigger.getCronExpression();
//			if (!oldTime.equalsIgnoreCase(time)) {
//				// 通过任务名和组名获取JobKey
//				JobKey jobKey = JobKey.jobKey(jobName, JOB_GROUP_NAME);
//				JobDetail jobDetail = scheduler.getJobDetail(jobKey);
//				Class<? extends Job> objJobClass = jobDetail.getJobClass();
//				removeJob(jobName);
//				addJob(jobName, objJobClass, time);
//			}
//		} catch (Exception e) {
//			throw new ExceptionPack("添加一个定时任务失败", e, ExceptionGradeEnum.WARN);
//		}
//	}
//
//	/**
//	 * 修改一个任务的cronStr表达式,startTime开始时间(SimpleTrigger直接add就好)
//	 *
//	 * @param triggerName      任务名称
//	 * @param triggerGroupName 传过来的任务名称
//	 * @param time             更新后的时间规则
//	 * @param   starTime      更新后的开始时间
//	 */
//	public void modifyJobTime(String triggerName, String triggerGroupName, String time,Date starTime) {
//		try {
//			// 通过触发器名和组名获取TriggerKey
//			TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
//			// 通过TriggerKey获取CronTrigger
//			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
//			if (trigger == null)
//				return;
//			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(trigger.getCronExpression());
//			String oldTime = trigger.getCronExpression();
//			if (!oldTime.equalsIgnoreCase(time)) {
//				// 重新构建trigger
//				trigger = (CronTrigger) trigger.getTriggerBuilder().withIdentity(triggerKey)
//						.withSchedule(scheduleBuilder).withSchedule(CronScheduleBuilder.cronSchedule(time)).startAt(starTime).build();
//				// 按新的trigger重新设置job执行
//				scheduler.rescheduleJob(triggerKey, trigger);
//			}
//		} catch (Exception e) {
//			throw new QuartzJobException(e);
//		}
//	}
//
//	/**
//	 * 只修改一个任务的startTime开始时间
//	 *
//	 * @param triggerName      任务名称
//	 * @param triggerGroupName 传过来的任务名称
//	 * @param   newStartTime      更新后的开始时间
//	 */
//	public void modifyJobTime(String triggerName, String triggerGroupName,Date newStartTime) {
//		try {
//			// 通过触发器名和组名获取TriggerKey
//			TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
//			// 通过TriggerKey获取CronTrigger
//			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
//			if (trigger == null)
//				return;
//			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(trigger.getCronExpression());
//			Date oldStartTime = trigger.getStartTime();
//			String oldTime = trigger.getCronExpression();
//			if (!oldStartTime.equals(newStartTime)) {
//				// 重新构建trigger
//				trigger = (CronTrigger) trigger.getTriggerBuilder().withIdentity(triggerKey)
//						.withSchedule(scheduleBuilder).withSchedule(CronScheduleBuilder.cronSchedule(oldTime)).startAt(newStartTime).build();
//				// 按新的trigger重新设置job执行
//				scheduler.rescheduleJob(triggerKey, trigger);
//			}
//		} catch (Exception e) {
//			throw new QuartzJobException(e);
//		}
//	}
//
//
//
//
//	/**
//	 * 移除一个任务(使用默认的任务组名，触发器名，触发器组名)
//	 *
//	 * @param jobName 任务名称
//	 */
//	public void removeJob(String jobName) {
//		try {
//			// 通过触发器名和组名获取TriggerKey
//			TriggerKey triggerKey = TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME);
//			// 通过任务名和组名获取JobKey
//			JobKey jobKey = JobKey.jobKey(jobName, JOB_GROUP_NAME);
//			// 停止触发器
//			scheduler.pauseTrigger(triggerKey);
//			// 移除触发器
//			scheduler.unscheduleJob(triggerKey);
//			// 删除任务
//			scheduler.deleteJob(jobKey);
//		} catch (Exception e) {
//			throw new QuartzJobException(e);
//		}
//	}
//
//
//
//
//
//	/**
//	 * 移除一个任务
//	 *
//	 * @param jobName          任务名
//	 * @param jobGroupName     任务组名
//	 * @param triggerName      触发器名
//	 * @param triggerGroupName 触发器组名
//	 */
//	public void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) {
//		try {
//			// 通过触发器名和组名获取TriggerKey
//			TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
//			// 通过任务名和组名获取JobKey
//			JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
//			// 停止触发器
//			scheduler.pauseTrigger(triggerKey);
//			// 移除触发器
//			scheduler.unscheduleJob(triggerKey);
//			// 删除任务
//			scheduler.deleteJob(jobKey);
//		} catch (Exception e) {
//			throw new QuartzJobException(e);
//		}
//	}
//
//	/**
//	 * 启动所有定时任务
//	 */
//	public void startJobs() {
//		try {
//			scheduler.start();
//		} catch (Exception e) {
//			throw new QuartzJobException(e);
//		}
//	}
//
//	/**
//	 * 关闭所有定时任务
//	 */
//	public void shutdownJobs() {
//		try {
//			if (!scheduler.isShutdown()) {
//				scheduler.shutdown();
//			}
//		} catch (Exception e) {
//			throw new QuartzJobException(e);
//		}
//	}
//
//	/**
//	 * 根据jobGroup得到所有 JobKey
//	 * @param jobGroup
//	 * @return
//	 */
//	public  Set<JobKey> listAll(String jobGroup) {
//		try {
//			return scheduler.getJobKeys(GroupMatcher.jobGroupEquals(jobGroup));
//
//		} catch (SchedulerException e) {
//			throw new QuartzJobException(e);
//		}
//	}
//
//	/**
//	 * 得到触发器的 StartTime
//	 * @return
//	 */
//	public  Date getStartTime(String triggerName,String triggerGroupName) {
//		try {
//			// 通过触发器名和组名获取TriggerKey
//			TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
//
//			return scheduler.getTrigger(triggerKey).getStartTime();
//
//		} catch (SchedulerException e) {
//			throw new QuartzJobException(e);
//		}
//	}
//
//	/**
//	 * 得到触发器的 cronStr
//	 * @return
//	 */
//	public  String getCronStr(String triggerName,String triggerGroupName) {
//		try {
//			// 通过触发器名和组名获取TriggerKey
//			TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
//			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
//			if (trigger == null){
//				return null;
//			}
//			return trigger.getCronExpression();
//
//		} catch (SchedulerException e) {
//			throw new QuartzJobException(e);
//		}
//	}
//	/**
//	 * 得到触发器的 StartTime  str形式
//	 * @return
//	 */
//	public  String getStartTimeStr(String triggerName,String triggerGroupName) {
//		if(getStartTime(triggerName,triggerGroupName)==null){
//			return "";
//		}
//		return dateTimeStr(getStartTime(triggerName,triggerGroupName));
//	}
//
//}
