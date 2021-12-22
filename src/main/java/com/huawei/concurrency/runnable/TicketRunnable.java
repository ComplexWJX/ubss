package com.huawei.concurrency.runnable;

import com.huawei.entity.Ticket;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 并发问题，同步
 * 简单的实现并发，限流
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

	private Ticket ticket;
	// 5张票
	/**
	 * 自增操作不是原子性操作，而且volatile也无法保证对变量的任何操作都是原子性的
	 * 采用synchronized或者lock进行同步保证“读-写-改”一致性
	 */

	/**
	 * 自增自减并非原子操作，所以声明为volatile也不能保证数据的准确性
	 */
	//private volatile  int total = 10;

	private AtomicInteger total = new AtomicInteger(10);

	private volatile  boolean  isStop = false;


	@Override
	public void run() {
			    snatchTicket(ticket);
	}

	/**
	 * 买票，多个线程都调用这个方法，相当于只提供一个窗口抢票
	 */
	private  void snatchTicket(Ticket ticket) {
	            while(!isStop){
	                String tName = Thread.currentThread().getName();
	                try
                    {
                        semaphore.acquire();
                        System.out.println("目前并发数:"+semaphore.availablePermits());
                        synchronized (this){
							if (total.get() > 0) {
								// 如果已经抢到2张该线程让出，使用信号量Semaphore限流
//                        if(ticket.getLimit() > 2){
//                            return;
//                        }else{

								//模拟买票时间
								Thread.sleep(500);
								ticket.set();
								System.out.println("current Thread:" + tName + "," + "抢到:" + ticket.getLimit() + "," + (total.decrementAndGet()) + " tickets left");
								semaphore.release();
//                          }

							}
							else{
								isStop = true;
								System.out.println("票已售罄");
								break;
							}
						}

                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }

	           }
	}
}
