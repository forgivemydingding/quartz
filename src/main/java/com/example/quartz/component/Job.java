package com.example.quartz.component;

import com.example.quartz.test.MyJobOne;
import com.example.quartz.test.SimpleTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author by liu.hongda
 * @Description TODO
 * @Date 2019/8/23 16:35
 */
public class Job {

    @Autowired
    SimpleTrigger simpleTrigger;

    public static void main(String[] args) {
        try {
            StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();

            Scheduler scheduler = stdSchedulerFactory.getScheduler();

            //开启线程
            scheduler.start();

            // define the job and tie it to our HelloJob class
            JobDetail job = newJob(MyJobOne.class)
                    .withIdentity("myJob", "group")
                    .usingJobData("weight",140)
                    .usingJobData("height",173)
                    .build();

            // Trigger the job to run now, and then every 40 seconds
            Trigger trigger = newTrigger()
                    .withIdentity("myTrigger", "group")
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(5)
                            .repeatForever())
                    .startNow()
                    .build();

            scheduler.scheduleJob(job,trigger);

            //scheduler.shutdown();

        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }

}
