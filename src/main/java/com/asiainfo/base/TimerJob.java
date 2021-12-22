package com.asiainfo.base;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerJob
{
    public static void main(String[] args) throws InterruptedException
    {
        Calendar cl = Calendar.getInstance();
        cl.add(Calendar.SECOND, 5);
        exe(cl.getTime(),new Date());
        Thread.sleep(6000);
    }

    @SuppressWarnings("deprecation")
    private static void exe(Date endTime,Date now)
    {
        Timer timer = new Timer(true);
        int delay = endTime.getSeconds() - now.getSeconds();

        timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                System.out.println("exe at:" + new Date());

            }
        }, delay*1000);
    }
}
