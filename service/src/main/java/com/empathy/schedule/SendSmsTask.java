package com.empathy.schedule;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SendSmsTask implements Job {



    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String phone = jobExecutionContext.getJobDetail().getJobDataMap().getString("phone");
// TODO: 2018/10/22 发送短信
        System.out.println("发送短信任务具体执行"+phone);
    }
}
