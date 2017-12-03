package com.smart.basic.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.ParseException;

/**
 * Created by shliangyan on 2017/10/24.
 */
public class CronTriggerFunner {
    public static void main(String[] args) {
        JobDetail jobDetail = new JobDetail("job_2", "jGroup1", SimpleJob.class);
        CronTrigger cronTrigger = new CronTrigger("trigger_2", "tgroup1");
        try {
            CronExpression cronExpression = new CronExpression("0/5 * * * * ?");
            cronTrigger.setCronExpression(cronExpression);

            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();
            scheduler.scheduleJob(jobDetail, cronTrigger);
            scheduler.start();
        } catch (ParseException | SchedulerException e) {
            e.printStackTrace();
        }
    }
}
