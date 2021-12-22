package com.huawei.concurrency.task;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.huawei.entity.Ticket;
import com.huawei.concurrency.thread.NamedThreadFactory;

/**
 * 任务抽象
 * @author WJX
 */
public class SellTask {
	private LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(1);

	/**
	 * 初始化任务
	 */
    public SellTask(Runnable r) {
		//使用put向队尾添加元素，如果队列中任务已满，会发生阻塞一直等待空间，以加入元素。
		addTask(r);
	}


    /**
     * 如果运行的线程多于 corePoolSize 而少于 maximumPoolSize，会首选将任务runnable添加到队列，当队列满时才创建新线程
     */
	private ExecutorService executor = new ThreadPoolExecutor(
			1, //核心线程数
			1, //最大线程数
			60L, //
			TimeUnit.SECONDS,
			workQueue,//这里的队列用于暂时存放任务，当有空闲线程时取出执行
			new NamedThreadFactory(UUID.randomUUID().toString(), new Thread.UncaughtExceptionHandler() {

				@Override
				public void uncaughtException(Thread t, Throwable e) {
					System.out.println(t.getName()+" execute failed.");
					e.printStackTrace();
				}
			})) {

	};

	/**
	 * 启动任务
	 */
	public void start() {

		if (!workQueue.isEmpty()) {
		    try {
                executor.execute(workQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
		}else{
		    stop();
		}
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
	 * 往队列中添加任务
	 */
	private void addTask(Runnable task){
		try {
			workQueue.put(task);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
