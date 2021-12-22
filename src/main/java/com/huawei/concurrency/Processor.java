package com.huawei.concurrency;

import com.huawei.entity.Ticket;
import com.huawei.concurrency.runnable.TicketRunnable;
import com.huawei.concurrency.task.SellTask;

import java.util.concurrent.Semaphore;

/**
 * 测试多线程并发
 * @author WJX
 * @date 2020-06-01
 */
public class Processor {
	public static void main(String[] args) {

	    final Semaphore semaphore = new Semaphore(4);
		TicketRunnable ticketRunnable = new TicketRunnable(new Ticket(), semaphore);
		for (int i = 0; i < 10; i++)
        {
	        new SellTask(ticketRunnable).start();
        }
	}
}
