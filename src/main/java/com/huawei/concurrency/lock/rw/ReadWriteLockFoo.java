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

    /**
     * 在使用阻塞等待获取锁的方式中，必须在try代码块之外，并且在加锁方法与try代码块之间没有任何可能抛出异常的方法调用，避免加锁成功后，在finally中无法解锁。
     * 说明一：如果在lock方法与try代码块之间的方法调用抛出异常，那么无法解锁，造成其它线程无法成功获取锁。
     * 说明二：如果lock方法在try代码块之内，可能由于其它方法抛出异常，导致在finally代码块中，unlock对未加锁的对象解锁，它会调用AQS的tryRelease方法（取决于具体实现类），抛出IllegalMonitorStateException异常。
     * 说明三：在Lock对象的lock方法实现中可能抛出unchecked异常，产生的后果与说明二相同。 java.concurrent.LockShouldWithTryFinallyRule.rule.desc
     * @throws InterruptedException
     */
    public void read() throws InterruptedException {
        readLock.lockInterruptibly();
        try {
            String name = Thread.currentThread().getName();
            if (Integer.parseInt(name.substring(6)) <= 3) {
                Thread.sleep(2000);
            }
            System.out.println(name + " read");
        } finally {
            readLock.unlock();
        }
    }

    public void write() throws InterruptedException {
        writeLock.lockInterruptibly();
        try {
            System.out.println(Thread.currentThread().getName() + " write");
        } finally {
            writeLock.unlock();
        }
    }

    public void writeBlock() throws InterruptedException {
        writeLock.lockInterruptibly();
        try {
            System.out.println(Thread.currentThread().getName() + " write");
            Thread.sleep(2000);
        } finally {
            writeLock.unlock();
        }
    }

    public void entrant() throws InterruptedException {
        writeLock.lock();
        try {
            String name = Thread.currentThread().getName();
            System.out.println(name + " entrant");
            reentrant();
        } finally {
            writeLock.unlock();
        }
    }

    public void reentrant() throws InterruptedException {
        writeLock.lock();
        try {
            String name = Thread.currentThread().getName();
            System.out.println(name + " reentrant");
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * 同个线程同时获取读写锁
     * @throws InterruptedException
     */
    public void getTwoLock() throws InterruptedException {
        writeLock.lock();
        try {
            String name = Thread.currentThread().getName();
            System.out.println(name + " write first");
            readLock.lock();
            System.out.println(name + " read after");
        } finally {
            readLock.unlock();
            writeLock.unlock();
        }
    }

    static void testReentrant() {
        ReadWriteLockFoo foo = new ReadWriteLockFoo();
        Thread t = new Thread(() -> {
            try {
                foo.entrant();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();
    }

    /**
     * 读锁共享，多个线程可以同时获取读锁
     * 获取读锁的前提，写锁没有被独占，或者独占写锁线程为当前线程
     */
    static void testSharedLock() {
        ReadWriteLockFoo foo = new ReadWriteLockFoo();
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(() -> {
                try {
                    foo.read();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            t.setName("thread" + i);
            t.start();
        }
    }

    static void testExclusiveLock() {
        ReadWriteLockFoo foo = new ReadWriteLockFoo();

        Thread t1 = new Thread(() -> {
            try {
                foo.writeBlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                //foo.write();
                foo.read();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.setName("thread1");
        t2.setName("thread2");
        t1.start();
        t2.start();
    }

    static void testGetTwoLockOneThread() {
        ReadWriteLockFoo foo = new ReadWriteLockFoo();

        Thread t1 = new Thread(() -> {
            try {
                foo.getTwoLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.setName("thread1");
        t1.start();
    }

    public static void main(String[] args) {
//        testExclusiveLock();
//        testGetTwoLockOneThread();
        testSharedLock();
    }
}
