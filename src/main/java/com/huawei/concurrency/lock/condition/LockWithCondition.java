package com.huawei.concurrency.lock.condition;

import com.itany.entity.Product;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition解决生产者消费者问题
 */
public class LockWithCondition {
    private final ReentrantLock lock = new ReentrantLock();

    Product[] products = new Product[5];

    private int total;

    Condition notFull = lock.newCondition();

    Condition notEmpty = lock.newCondition();

    public void put () {
        lock.lock();
        try {
            if (total < products.length - 1) {
                products[total] = new Product();
                total++;
                System.out.println("生产第" + total + "个产品");
                notEmpty.signal();
            }else {
                notFull.await();
            }

        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void take () {
        lock.lock();
        try {
            if (total <= 0) {
                System.out.println("已无库存");
                notFull.signal();
            } else {
                products[total] = null;
                total--;
                System.out.println("剩余" + total + "个产品");
                notEmpty.await();
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
