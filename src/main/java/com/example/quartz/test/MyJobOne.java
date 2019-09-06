package com.example.quartz.test;

import org.quartz.*;

/**
 * @author by liu.hongda
 * @Description TODO
 * @Date 2019/9/4 15:42
 */

public class MyJobOne implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        JobKey key = context.getJobDetail().getKey();

        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        Object weight = jobDataMap.get("weight");
        Object height = jobDataMap.get("height");

        System.out.println("JobKey:" + key + "   weight:" + weight + "   height:" + height);

        //System.out.println("Hello!  HelloJob is executing.");
    }
}
