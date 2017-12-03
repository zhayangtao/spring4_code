package com.smart.basic.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by shliangyan on 2017/10/24.
 */
public class JDBCJobStoreRunner {
    public static void main(String[] args) throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        String[] triggerGroups = scheduler.getTriggerGroupNames();

        for (int i = 0; i < triggerGroups.length; i++) {
            String[] triggers = scheduler.getTriggerNames(triggerGroups[i]);
            for (int j = 0; j < triggers.length; j++) {
                Trigger trigger = scheduler.getTrigger(triggers[j], triggerGroups[i]);
                if (trigger instanceof SimpleTrigger && "tgroup1.trigger1_1".equals(trigger.getFullName())) {
                    scheduler.rescheduleJob(triggers[j], triggerGroups[i], trigger);// 恢复运行
                }
            }
            scheduler.start();
        }
    }
}
