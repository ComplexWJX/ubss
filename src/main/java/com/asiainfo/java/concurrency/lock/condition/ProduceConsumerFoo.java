package com.asiainfo.java.concurrency.lock.condition;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProduceConsumerFoo {
    ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        ProduceConsumerFoo foo = new ProduceConsumerFoo();
        //foo.sign();
        foo.startQueue();
    }

    void startQueue() {
        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(50);

        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 30, TimeUnit.SECONDS, queue);

        MyBlockingQueue store = new MyBlockingQueue();

        for (int i = 0; i < 5; i++) {
            executor.execute(new Producer(store));
        }

        for (int i = 0; i < 5; i++) {
            executor.execute(new Consumor(store));
        }

        while (executor.getActiveCount() > 0) {
            try {
                Thread.sleep(3000);
                System.out.println("活跃线程数：" + executor.getActiveCount());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
    }

    void sign() {
        Condition condition = lock.newCondition();
        lock.lock();
        condition.signal();
        lock.unlock();
    }
}

