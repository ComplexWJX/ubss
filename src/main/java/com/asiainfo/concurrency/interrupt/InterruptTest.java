package com.asiainfo.concurrency.interrupt;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author WJX
 * @title: InterruptTest
 * @projectName ubss
 * @description: TODO
 * @date 2020/6/20 0020
 */
public class InterruptTest {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
//        interruptByFuture();
    }

    private static void interruptByThread(){
        Thread t1 = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()){
                System.out.println("execute");
            }
        });

        Thread t2 = new Thread(t1::interrupt);

        t1.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }

    private static void interruptByFuture(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        AtomicInteger count = new AtomicInteger(500);
        Future<?> future = executorService.submit(() -> {
            // TODO 调用耗时方法...
        });

        try {
            future.get(10, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            future.cancel(true);
            e.printStackTrace();
        }finally {
            //如果任务已经结束，执行取消操作不会有任何影响，
            // 如果任务正在运行，将会被中断
            future.cancel(true);
        }

    }
}
