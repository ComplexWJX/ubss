package com.jaxon.java.concurrency;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.jaxon.java.concurrency.task.TicketRunnable;
import com.jaxon.java.concurrency.thread.NamedThreadFactory;

/**
 * 任务处理器
 * @author WJX
 */
public class TaskExecutor {
	private LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(10);

	/**
	 * 如果运行的线程多于 corePoolSize 而少于 maximumPoolSize，会首选将任务runnable添加到队列，当队列满时才创建新线程
	 */
	private ExecutorService executor;

	public TaskExecutor() {
		initialize();
	}

	void initialize() {
		/**
		 * 1.corePoolSize：核心线程数。当线程池的线程数量<corePoolSize，则创建线程，执行任务
		 * 2.workQueue：阻塞队列。当线程池的线程数量>=corePoolSize，则会尝试将任务放入该队列。比较常见的是基于数组实现的ArrayBlockingQueue和基于链表实现的LinkedBlockingQueue
		 * 3.maximumPoolSize：最大线程数。当线程池的线程数量>=corePoolSize，并且阻塞队列workQueue已满时，同时当线程池的线程数量<maximumPoolSize，则创建线程，执行任务
		 * 4.handler：拒绝执行策略。当阻塞队列workQueue已满，并且线程池的线程数量>=maximumPoolSize时，则会执行拒绝策略。
		 *   RejectedExecutionHandler 接口有4个实现类：AbortPolicy（抛出异常）、CallerRunsPolicy（调用者线程执行任务）、DiscardPolicy（直接丢弃任务）、DiscardOldestPolicy（丢弃等待最久的任务），默认是AbortPolicy，直接抛出异常。我们也可以实现RejectedExecutionHandler接口，自定义拒绝策略。
		 * 5.threadFactory：线程工厂。它的默认实现是DefaultThreadFactory，只是负责创建非守护线程，线程优先级是Thread.NORM_PRIORITY的线程。
		 * 6.keepAliveTime：存活时间。线程池中工作线程空闲时间达到keepAliveTime时，就会被销毁。
		 * 7.unit：存活时间的单位。TimeUnit 里面的枚举值
		 */
		executor = new ThreadPoolExecutor(
				4, //核心线程数
				8, //最大线程数
				60L, //
				TimeUnit.SECONDS,
				workQueue,//这里的队列用于暂时存放任务，当有空闲线程时取出执行
				new NamedThreadFactory("Sell Thread", new Thread.UncaughtExceptionHandler() {
					@Override
					public void uncaughtException(Thread t, Throwable e) {
						System.out.println(t.getName()+" execute failed.");
						e.printStackTrace();
					}
				})) {
		};
	}

	/**
	 * 往队列中添加任务
	 */
	public void addTask(TicketRunnable task){
		try {
			workQueue.put(task);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 启动任务
	 */
	public void start() {
		while (!workQueue.isEmpty()) {
		    try {
                executor.execute(workQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
		}
		stop();
	}

	/**
	 * Runnable任务抽象
	 */
	public void excute(){
	    try
        {
            executor.execute(workQueue.take());
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
	}

	/**
	 * 提交callable，单个任务
	 */
	public <T> Future<T> excute(Callable<T> callable){
	    Future<T> future = executor.submit(callable);
	    return future;
	}

	/**
     * 提交callable，批量任务
     */
    public <T> List<Future<T>> excute(List<Callable<T>> tasks){
        List<Future<T>> futures = null;
        try
        {
            futures = executor.invokeAll(tasks);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        return futures;
    }

	/**
	 * 关闭任务
	 */
	public void stop() {
		executor.shutdown();
		try
        {
            executor.awaitTermination(2, TimeUnit.SECONDS);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
	}
}
