package com.asiainfo.java.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.function.Function;

/**
 * [时间轮定时器实现]
 *
 * 简单定时器实现，抽象一个60s的秒针表，通过delay延迟计算出执行所在位置index，将任务存放在对应数组位置上，循环获取数组元素，如果取得任务则执行
 *
 * @author rukawa
 * Created on 2023/03/15 9:48 by rukawa
 */
public class TimerWheel {

    private static Logger logger = LoggerFactory.getLogger(TimerWheel.class);

    private Object [] jobTimerTable = new Object[60];

    public static void main(String[] args) {
        TimerWheel timerWheel = new TimerWheel();
        timerWheel.putJob(3, new MyJob());

        LocalDateTime start = LocalDateTime.now();
        logger.info("add job at {}", start);

        while (true) {
            Duration duration = Duration.between(start, LocalDateTime.now());
            int delay = (int) duration.getSeconds();
            Object job = timerWheel.getJob(delay);
            if (job != null) {
                if (job instanceof Function) {
                    ((Function) job).apply(delay + "s");

                    timerWheel.removeJob(timerWheel.calcIndex(delay));
                }
            }
        }
    }

    public void putJob(int delay, Object job) {

        jobTimerTable[calcIndex(delay)] = job;
    }

    public void removeJob(int index) {

        jobTimerTable[index] = null;
    }

    public int calcIndex(int delay) {
        return delay % jobTimerTable.length;
    }

    public Object getJob(int delay) {
        return jobTimerTable[calcIndex(delay)];
    }

    static class MyJob implements Function<String, String> {

        @Override
        public String apply(String s) {
            logger.info("exec successfully at {} ", LocalDateTime.now());
            System.out.println("");
            return "exec successfully.";
        }
    }
}
