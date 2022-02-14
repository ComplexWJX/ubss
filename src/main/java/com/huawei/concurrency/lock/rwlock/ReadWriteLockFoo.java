package com.huawei.concurrency.lock.rwlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author WJX
 * @date 2022-02-13
 */
public class ReadWriteLockFoo {
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    public void read() throws InterruptedException {
        Lock readLock = readWriteLock.readLock();
        try {
            readLock.lockInterruptibly();
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + "read");
        }finally {
            readLock.unlock();
        }
    }
    public void write() throws InterruptedException {
        Lock writeLock = readWriteLock.writeLock();
        try {
            writeLock.lockInterruptibly();
            System.out.println(Thread.currentThread().getName() + "write");
        }finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteLockFoo foo = new ReadWriteLockFoo();
        Thread t1 = new Thread(() -> {
            try {
                foo.read();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                foo.write();
                foo.read();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.setName("t1");
        t2.setName("t2");
        t1.start();
        t2.start();
    }
}
