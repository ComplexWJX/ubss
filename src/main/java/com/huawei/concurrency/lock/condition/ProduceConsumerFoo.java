package com.huawei.concurrency.lock.condition;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ProduceConsumerFoo {
    public static void main(String[] args) {

        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(50);

        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 4, 30, TimeUnit.SECONDS, queue);

        LockWithCondition store = new LockWithCondition();

        for (int i = 0; i < 20; i++) {
            executor.execute(store::put);
        }

        for (int i = 0; i < 20; i++) {
            executor.execute(store::take);
        }
    }

}
