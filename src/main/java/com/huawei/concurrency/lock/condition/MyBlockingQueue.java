package com.huawei.concurrency.lock.condition;

import com.itany.entity.Product;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition解决生产者消费者问题
 */
public class MyBlockingQueue {
    private final ReentrantLock putLock = new ReentrantLock();

    private final ReentrantLock takeLock = new ReentrantLock();

    Product[] products;

    private AtomicInteger total = new AtomicInteger(0);

    Condition notFull = putLock.newCondition();

    Condition notEmpty = takeLock.newCondition();

    int capital;

    public MyBlockingQueue() {
        this(5);
    }

    public MyBlockingQueue(int capital) {
        this.capital = capital;
        products = new Product[this.capital];
    }
    public void put () {
        int c = -1;
        putLock.lock();
        try {
            int count = total.get();
            while (count == this.capital) {
                notFull.await();
            }
            products[total.get()] = new Product();
            c = total.getAndIncrement();
            System.out.println("生产第" + (c + 1) + "个产品");
            Thread.sleep(1000);
            // 增加一个之后还未满，则可以通知其他put线程继续添加
            if (c + 1 < capital) {
                notFull.signal();
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            putLock.unlock();
        }
        // 如果放入之前队列中无元素，则通知消费者线程消费
        if (c == 0) {
            signalNotEmpty();
        }
    }

    public void take () {
        int c = -1;
        takeLock.lock();
        try {
            while (total.get() == 0) {
                System.out.println("已无库存");
                notEmpty.await();
            }
            products[total.get() - 1] = null;
            Thread.sleep(1000);
            c = total.getAndDecrement();
            System.out.println("剩余" + (total.get()) + "个产品");
            // 扣除之前如果大于1个，则本次扣除之后可以继续通知其他take线程扣除
            if (c > 1) {
                notEmpty.signal();
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            takeLock.unlock();
        }
        // 移除元素之前队列是满的，唤醒生产线程进行添加元素
        signalNotFull();
    }

    /**
     * 1.为什么要先获取锁，才能向该锁上创建的condition等待线程队列发送信号通知？因为signal会检查当前线程是否独占锁
     * 2.signal移动条件等待队列中等待时间最久的线程到lock锁竞争队列中
     */
    private void signalNotEmpty() {
        final ReentrantLock takeLock = this.takeLock;
        takeLock.lock();
        try {
            notEmpty.signal();
        } finally {
            takeLock.unlock();
        }
    }

    private void signalNotFull() {
        final ReentrantLock putLock = this.putLock;
        putLock.lock();
        try {
            notFull.signal();
        } finally {
            putLock.unlock();
        }
    }
}
