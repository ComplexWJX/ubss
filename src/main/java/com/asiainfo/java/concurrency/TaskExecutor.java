package com.asiainfo.java.concurrency;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.asiainfo.java.concurrency.task.TicketRunnable;
import com.asiainfo.java.concurrency.thread.NamedThreadFactory;

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
