package com.example.quartz.test;

import org.quartz.*;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author by liu.hongda
 * @Description TODO
 * @Date 2019/9/5 15:56
 */

public class MyJobTwo implements Job {

    String jobSays;
    float myFloatValue;
    ArrayList state;


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobKey key = context.getJobDetail().getKey();

        JobDataMap mergedJobDataMap = context.getMergedJobDataMap();

        state.add(new Date());

        System.out.println("Instance " + key + " of DumbJob says: " + jobSays + ", and val is: " + myFloatValue);

    }

    public void setJobSays(String jobSays) {
        this.jobSays = jobSays;
    }

    public void setMyFloatValue(float myFloatValue) {
        this.myFloatValue = myFloatValue;
    }

    public void setState(ArrayList state) {
        this.state = state;
    }
}
