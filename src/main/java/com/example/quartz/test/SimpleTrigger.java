package com.example.quartz.test;

import org.quartz.DateBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.springframework.stereotype.Component;

import java.util.Date;

import static org.quartz.DateBuilder.dateOf;
import static org.quartz.DateBuilder.futureDate;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author by liu.hongda
 * @Description TODO
 * @Date 2019/9/6 9:35
 */
@Component
public class SimpleTrigger {

    private SimpleTrigger trigger;

    /**
     * 指定时间开始触发，不重复
     *
     * @param myStartTime
     */
    public void timeNoRepeat(Date myStartTime) {
        trigger = (SimpleTrigger) newTrigger()
                .withIdentity("trigger1", "group1")
                // some Date
                .startAt(myStartTime)
                // identify job with name, group strings
                .forJob("job1", "group1")
                .build();
    }

    /**
     * 指定时间触发，每隔10秒执行一次，重复10次
     *
     * @param myTimeToStartFiring
     * @param myJob
     */
    public void tenAndTen(Date myTimeToStartFiring, JobDetail myJob) {
        trigger = (SimpleTrigger) newTrigger()
                .withIdentity("trigger3", "group1")
                // if a start time is not given (if this line were omitted), "now" is implied
                .startAt(myTimeToStartFiring)
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(10)
                        // note that 10 repeats will give a total of 11 firings
                        .withRepeatCount(10))
                // identify job with handle to its JobDetail itself
                .forJob(myJob)
                .build();
    }

    /**
     * 5分钟以后开始触发，仅执行一次
     *
     * @param myJobKey
     */
    public void fiveButOne(JobKey myJobKey) {
        trigger = (SimpleTrigger) newTrigger()
                .withIdentity("trigger5", "group1")
                // use DateBuilder to create a date in the future
                .startAt(futureDate(5, DateBuilder.IntervalUnit.MINUTE))
                // identify job with its JobKey
                .forJob(myJobKey)
                .build();
    }

    /**
     * 立即触发，每个5分钟执行一次，直到22:00
     */
    public void fiveMinUtilTen() {
        trigger = (SimpleTrigger) newTrigger()
                .withIdentity("trigger7", "group1")
                .withSchedule(simpleSchedule()
                        .withIntervalInMinutes(5)
                        .repeatForever())
                .endAt(dateOf(22, 0, 0))
                .build();
    }
}
