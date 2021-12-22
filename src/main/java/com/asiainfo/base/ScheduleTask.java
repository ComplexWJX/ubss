package com.asiainfo.base;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleTask
{
    public static void main(String[] args)
    {
        ScheduledExecutorService  executorService = Executors.newSingleThreadScheduledExecutor();
        Runnable runnable = new Runnable()
        {
            public void run()
            {
                System.out.println("定时任务执行了");
            }
        };

        //如果想要在某一时刻定时执行，需计算出时差
        executorService.scheduleAtFixedRate(runnable, 1, 5, TimeUnit.SECONDS);
        try
        {
            Thread.sleep(3000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        executorService.shutdown();
    }
}
