package com.jaxon.java.concurrency.lock.queue;

import com.jaxon.java.concurrency.lock.QueueConstants;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created with IntelliJ IDEA.
 *
 * @author WJX
 * @version 1.0
 * @date 2023/11/27/17:39
 * @description
 */
public class BlockQueueFoo {
    public static void main(String[] args) {

        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(QueueConstants.ONE_BILLION_NUM);

        final long start = System.currentTimeMillis();

        new Thread(() -> {
            for (int i = 0; i < QueueConstants.ONE_BILLION_NUM; i++) {
                try {
                    blockingQueue.put(i + "");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("put耗时：" + ((System.currentTimeMillis() - start)) + "ms");
        }).start();


        new Thread(() -> {
            for (int i = 0; i < QueueConstants.ONE_BILLION_NUM; i++) {
                try {
                    blockingQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("get耗时：" + ((System.currentTimeMillis() - start)) + "ms");
        }).start();
    }
}
