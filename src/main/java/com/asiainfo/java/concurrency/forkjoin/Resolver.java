package com.asiainfo.java.concurrency.forkjoin;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author WJX
 * @title: Resolver
 * @projectName ubss
 * @description: TODO
 * @date 2020/6/1 0001
 */
public class Resolver {

//    SimpleDateFormat并发情况下会有错误
//    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private List<String> operateList;

    private List<String> resultList;

    public Resolver(List<String> operateList,List<String> resultList) {
        this.operateList = operateList;
        this.resultList = resultList;
    }

    public void solve(int start, int end, CountDownLatch latch){
        // 不加锁是因为CountDownLatch countDown方法是CAS实现，线程安全
        // TODO 线程执行过程中如果不处理异常，会挂起阻塞
//        int num = 1/0;
        for (int i = start; i < end; i++) {
            try {
                LocalDateTime localDateTime = LocalDateTime.parse(operateList.get(i),formatter);
                Date date = localDateTime2Date(localDateTime);
                System.out.println(date);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //多线程并发导致List的add()失败，元素为null
           /* try {
                Thread.sleep(50);
                resultList.add(operateList.get(i));
            } catch (Exception e) {
                e.printStackTrace();
            }*/

            latch.countDown();
        }
    }

    private Date localDateTime2Date(LocalDateTime localDateTime){
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }
}
