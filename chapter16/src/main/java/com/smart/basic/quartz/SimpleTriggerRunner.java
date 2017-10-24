package com.smart.basic.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * 调用job实例
 * 1、创建JobDetail
 * 2、创建SimpleTrigger
 * 3、创建Scheduler
 * Created by shliangyan on 2017/10/24.
 */
public class SimpleTriggerRunner {
    public static void main(String[] args) {
        JobDetail jobDetail = new JobDetail("job_1", "jgroup1", SimpleJob.class);
        SimpleTrigger simpleTrigger = new SimpleTrigger("trigger_1", "tgropu1");
        simpleTrigger.setStartTime(new Date());
        simpleTrigger.setRepeatInterval(2000);
        simpleTrigger.setRepeatCount(10);

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();
            scheduler.scheduleJob(jobDetail, simpleTrigger);
            scheduler.start();// 启动job
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
