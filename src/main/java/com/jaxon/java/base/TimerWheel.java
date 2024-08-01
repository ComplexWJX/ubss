package com.jaxon.java.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

/**
 * [时间轮定时器实现]
 * <p>
 * 1.分层时间轮定时器实现，用3个数组抽象组成时钟，根据给定执行时间
 * 2.计算出执行所在位置index，每当指针指到当层格子时，将任务映射并复制到上一层
 * 3.循环每次根据当前时分秒模运算获取应查找数组index，如果取得任务则执行
 *
 * 示例：从15:56:11启动任务，定时到16:13:53执行任务，先将任务放在hourTable的16%24的位置，等到16时，将hourTable16%24位置
 *      的任务复制到minuteTable的13%60位置，以此类推，再将minuteTable的13%60复制到secondTable的53%60位置，
 *      每次遍历如果最终查询secondTable在【当前秒数%60】位置有任务则执行成功。
 *
 * 时间轮定时器的时间复杂度为O(1)，直接使用链表或者队列，复杂度O(n)
 *
 * 时针：0 1 2 ... 24
 * 分针：0 1 2 ... 60
 * 秒针：0 1 2 ... 60
 *
 * @author rukawa
 * Created on 2023/03/15 9:48 by rukawa
 */
public class TimerWheel {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimerWheel.class);

    private final MyJob[] secondTable = new MyJob[60];

    private final MyJob[] minuteTable = new MyJob[60];

    private final MyJob[] hourTable = new MyJob[24];

    private static final DateTimeFormatter PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {

        LocalDateTime time = LocalDateTime.from(PATTERN.parse("2023-03-16 16:16:53"));

        TimerWheel timerWheel = new TimerWheel();
        MyJob myJob = new MyJob(time);
        timerWheel.putJob(myJob);

        LOGGER.info("add job at {}", PATTERN.format(LocalDateTime.now()));

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
            LOGGER.info("executed successfully at {} ", PATTERN.format(LocalDateTime.now()));
            return "str is : {" + s + "} successfully.";
        }
    }
}
