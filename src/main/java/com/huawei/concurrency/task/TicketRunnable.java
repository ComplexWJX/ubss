package com.huawei.concurrency.task;

import com.huawei.concurrency.Ticket;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 并发问题，同步
 * 简单的实现并发，限流
 *
 * @author Thinkpad
 * 2018年10月16日
 */

public class TicketRunnable implements Runnable {

    public TicketRunnable(Ticket ticket, Semaphore semaphore) {
        this.ticket = ticket;
        this.semaphore = semaphore;
    }

    /**
     * 信号量用于限流
     */
    private Semaphore semaphore;

    private final Ticket ticket;
    // 5张票
    /**
     * 自增操作不是原子性操作，而且volatile也无法保证对变量的任何操作都是原子性的
     * 采用synchronized或者lock进行同步保证“读-写-改”一致性
     */

    private volatile boolean isStop = false;

    @Override
    public void run() {
        snatchTicket(ticket);
    }

    /**
     * 买票，多个线程都调用这个方法，相当于只提供一个窗口抢票
     */
    private void snatchTicket(Ticket ticket) {
        while (!isStop) {
            String tName = Thread.currentThread().getName();
            try {
                //semaphore.acquire();
                //System.out.println("目前并发数:" + semaphore.availablePermits());
                synchronized (this.ticket) {
                    if (ticket.getTotal() > 0) {
                        // 如果已经抢到2张该线程让出，使用信号量Semaphore限流
//                        if(ticket.getLimit() > 2){
//                            return;
//                        }else{

                        //模拟买票时间
                        Thread.sleep(100);
                        ticket.set();
                        System.out.println("current Thread:" + tName + "," + "抢到:" + ticket.getLimit() + "," + (ticket.getTotal()) + " tickets left");
                        //semaphore.release();
//                          }

                    } else {
                        isStop = true;
                        System.out.println("票已售罄");
                        break;
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
