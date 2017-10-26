package com.smart.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;

import java.util.Map;

/**
 * Created by shliangyan on 2017/10/24.
 * 如果MyJob 实现了Job，则对JobDataMap做的更改对于下一次执行不可见，
 * 若实现了StatefulJob，则更改对于下一次执行可见。
 */
public class MyJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Map jobDetailDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        String size = (String) jobDetailDataMap.get("size");

        ApplicationContext context = (ApplicationContext) jobDetailDataMap.get("applicationContext");
        System.out.println("size:" + size);
        jobDetailDataMap.put("size", size + "0");

        Map triggerDataMap = jobExecutionContext.getTrigger().getJobDataMap();
        String count = (String) triggerDataMap.get("count");
        triggerDataMap.put("count", 30);
    }
}
