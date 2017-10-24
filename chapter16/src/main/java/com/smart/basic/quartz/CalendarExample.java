package com.smart.basic.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.AnnualCalendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * job任务：
 * 每小时运行一次，将五一劳动节、国庆节排除
 * Created by shliangyan on 2017/10/24.
 */
public class CalendarExample {
    public static void main(String[] args) throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        AnnualCalendar annualCalendar = new AnnualCalendar();
        Calendar calendar = new GregorianCalendar();// 5月1
        calendar.add(Calendar.MONTH, 5);
        calendar.add(Calendar.DATE, 1);

        Calendar calendar1 = new GregorianCalendar();// 10月1
        calendar1.add(Calendar.MONTH, 10);
        calendar1.add(Calendar.DATE, 1);

        ArrayList<Calendar> calendars = new ArrayList<>();
        calendars.add(calendar);
        calendars.add(calendar1);

        annualCalendar.setDaysExcluded(calendars);// 将日期排除

        scheduler.addCalendar("holidays", annualCalendar, false, false);

        Date date = TriggerUtils.getDateOf(0, 0, 10, 1, 4);// 4月1日上午10点
        JobDetail jobDetail = new JobDetail("job1", "group1", SimpleJob.class);
        SimpleTrigger trigger = new SimpleTrigger("trigger1", "group1",
                date, null, SimpleTrigger.REPEAT_INDEFINITELY, 60L * 60L * 1000);
        trigger.setCalendarName("holidays");

        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
    }
}
