package com.huawei.concurrency;

import com.huawei.concurrency.task.TicketRunnable;

import java.util.concurrent.Semaphore;

/**
 * 测试多线程并发
 *
 * @author WJX
 * @date 2020-06-01
 */
public class Processor {
    public static void main(String[] args) {
        try {
            TaskExecutor taskExecutor = new TaskExecutor();
            final Semaphore semaphore = new Semaphore(4);
            Ticket ticket = new Ticket();
            for (int i = 0; i < 10; i++) {
                TicketRunnable ticketRunnable = new TicketRunnable(ticket, semaphore);
                taskExecutor.addTask(ticketRunnable);
            }
            taskExecutor.start();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
