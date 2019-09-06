package com.example.quartz.test;

import org.quartz.DateBuilder;
import org.quartz.JobKey;
import org.quartz.Trigger;
import org.springframework.stereotype.Component;

import java.util.TimeZone;

import static org.quartz.CronScheduleBuilder.*;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author by liu.hongda
 * @Description TODO
 * @Date 2019/9/6 14:56
 */
@Component
public class CronTrigger {
    private Trigger trigger;

    /**
     * 建立一个触发器，每隔一分钟，每天上午8点至下午5点之间
     */
    public void one() {
        trigger = newTrigger()
                .withIdentity("trigger3", "group1")
                .withSchedule(cronSchedule("0 0/2 8-17 * * ?"))
                .forJob("myJob", "group1")
                .build();
    }

    /**
     * 建立一个触发器，将在上午10:42每天发射
     *
     * @param myJobKey
     * @param how      1为通用
     */
    public void two(JobKey myJobKey, int how) {
        if (how == 1) {
            trigger = newTrigger()
                    .withIdentity("trigger3", "group1")
                    .withSchedule(dailyAtHourAndMinute(10, 42))
                    .forJob(myJobKey)
                    .build();
        } else {
            trigger = newTrigger()
                    .withIdentity("trigger3", "group1")
                    .withSchedule(cronSchedule("0 42 10 * * ?"))
                    .forJob(myJobKey)
                    .build();
        }
    }

    /**
     * 建立一个触发器，将在星期三上午10:42在TimeZone（系统默认值）之外触发
     *
     * @param myJobKey
     * @param how      1为通用
     */
    public void three(JobKey myJobKey, int how) {
        if (how == 1) {
            trigger = newTrigger()
                    .withIdentity("trigger3", "group1")
                    .withSchedule(weeklyOnDayAndHourAndMinute(DateBuilder.WEDNESDAY, 10, 42))
                    .forJob(myJobKey)
                    //.inTimeZone(TimeZone.getTimeZone("America/Los_Angeles"))
                    .build();
        } else {
            trigger = newTrigger()
                    .withIdentity("trigger3", "group1")
                    .withSchedule(cronSchedule("0 42 10 ? * WED"))
                    //.inTimeZone(TimeZone.getTimeZone("America/Los_Angeles"))
                    .forJob(myJobKey)
                    .build();
        }
    }
}
