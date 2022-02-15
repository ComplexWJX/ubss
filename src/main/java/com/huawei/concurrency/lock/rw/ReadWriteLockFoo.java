package com.huawei.concurrency.lock.rw;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author WJX
 * @date 2022-02-13
 */
public class ReadWriteLockFoo {
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private final Lock readLock = readWriteLock.readLock();

    private final Lock writeLock = readWriteLock.writeLock();

    public void read() throws InterruptedException {
        try {
            readLock.lockInterruptibly();
            String name = Thread.currentThread().getName();
            if (Integer.parseInt(name.substring(6)) <=3)
            Thread.sleep(2000);
            System.out.println(name + " read");
        }finally {
            readLock.unlock();
        }
    }
    public void write() throws InterruptedException {
        try {
            writeLock.lockInterruptibly();
            System.out.println(Thread.currentThread().getName() + "write");
        }finally {
            writeLock.unlock();
        }
    }

    public void entrant() throws InterruptedException {
        try {
            writeLock.lock();
            String name = Thread.currentThread().getName();
            System.out.println(name + " entrant");
            reentrant();
        }finally {
            writeLock.unlock();
        }
    }

    public void reentrant() throws InterruptedException {
        try {
            writeLock.lock();
            String name = Thread.currentThread().getName();
            System.out.println(name + " reentrant");
        }finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteLockFoo foo = new ReadWriteLockFoo();
        Thread t1 = new Thread(() -> {
            try {
                foo.entrant();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
//        Thread t2 = new Thread(() -> {
//            try {
//                //foo.write();
//                foo.read();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//
        t1.setName("t1");
//        t2.setName("t2");
        t1.start();
//        t2.start();
//        for (int i = 0; i < 10; i++) {
//            Thread t = new Thread(() -> {
//                try {
//                    foo.read();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            });
//            t.setName("thread" + i);
//            t.start();
//        }
    }
}
