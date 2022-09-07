package com.asiainfo.java.concurrency.lock.heavy;

public class SynchronizeRealized {
    public synchronized void method1() {

    }

    public void method2() {
        synchronized(this) {

        }
    }
}
