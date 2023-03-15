package com.asiainfo.java.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

/**
 * [时间轮定时器实现]
 * <p>
 * 简单定时器实现，用3个数组抽象组成时钟，通过delay延迟计算出执行所在位置index，将任务存放在对应数组位置上，循环获取数组元素，如果取得任务则执行
 * 时针：0 1 2 ... 24
 * 分针：0 1 2 ... 60
 * 秒针：0 1 2 ... 60
 *
 * @author rukawa
 * Created on 2023/03/15 9:48 by rukawa
 */
public class TimerWheel {

    private static Logger logger = LoggerFactory.getLogger(TimerWheel.class);

    private MyJob[] secondTable = new MyJob[60];

    private MyJob[] minuteTable = new MyJob[60];

    private MyJob[] hourTable = new MyJob[24];

    public static void main(String[] args) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime time = LocalDateTime.from(pattern.parse("2023-03-15 18:01:43"));
        TimerWheel timerWheel = new TimerWheel();
        MyJob myJob = new MyJob(time);
        timerWheel.putJob(myJob);

        LocalDateTime start = LocalDateTime.now();
        logger.info("add job at {}", start);

        while (true) {
            MyJob job = timerWheel.getJob();
            if (job != null) {

                job.apply("hello");

                timerWheel.removeJob(myJob);
            }
        }
    }

    public void putJob(MyJob job) {

        int hIdx = job.getExecTime().getHour() % hourTable.length;

        hourTable[hIdx] = job;

    }

    public MyJob getJob() {
        return getOrReSetJob();
    }

    private MyJob getOrReSetJob() {
        LocalDateTime now = LocalDateTime.now();
        int hIdx = now.getHour() % hourTable.length;
        int mIdx = now.getMinute() % minuteTable.length;
        int sIdx = now.getSecond() % secondTable.length;

        if (hourTable[hIdx] != null && minuteTable[mIdx] == null) {
            LocalDateTime execTime = hourTable[hIdx].getExecTime();
            int mIdxNew = execTime.getMinute() % minuteTable.length;
            minuteTable[mIdxNew] = hourTable[hIdx];
        }

        if (minuteTable[mIdx] != null && secondTable[sIdx] == null) {
            LocalDateTime execTime = minuteTable[mIdx].getExecTime();
            int sIdxNew = execTime.getSecond() % secondTable.length;
            secondTable[sIdxNew] = minuteTable[mIdx];
        }

        return secondTable[sIdx];
    }

    public void removeJob(MyJob job) {
        LocalDateTime execTime = job.getExecTime();
        int hIdx = execTime.getHour() % hourTable.length;
        int mIdx = execTime.getMinute() % minuteTable.length;
        int sIdx = execTime.getSecond() % secondTable.length;

        hourTable[hIdx] = null;
        minuteTable[mIdx] = null;
        secondTable[sIdx] = null;
    }

    static class MyJob implements Function<String, String> {

        private LocalDateTime execTime;

        public MyJob(LocalDateTime execTime) {
            this.execTime = execTime;
        }

        public LocalDateTime getExecTime() {
            return execTime;
        }

        @Override
        public String apply(String s) {
            logger.info("exec successfully at {} ", LocalDateTime.now());
            return "str is : {" + s + "} successfully.";
        }
    }
}
