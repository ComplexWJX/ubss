package com.jaxon.java.concurrency.lock.heavy;

import org.junit.Test;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: WJX
 * @Date: 2021/06/08/16:27
 * @Description:
 */
public class SynchronizeFoo {

    @Test
    public void testSleepAndWait() {
        Thread t1 = new Thread(() -> methodA("A"));
        Thread t2 = new Thread(() -> methodA("B"));
        t1.start();
        t2.start();
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final Object lock = new Object();

    private synchronized void lockMethod(String name) {
        try {
            System.out.println("lockMethod:");
            lock.wait(2000);
            System.out.println(name + " release lock");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void methodA(String name) {
        synchronized (SynchronizeFoo.class) {
            try {
                System.out.println("name:" + name);
                lock.wait(2000);
                System.out.println(name + " release lock");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //java.lang.IllegalMonitorStateException
    private void methodB() {
        synchronized (this) {
            try {
                wait(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("methodB");
        }
    }
}
