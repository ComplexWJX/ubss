package com.huawei.concurrency.lock.condition;

public class Producer implements Runnable{
    private MyBlockingQueue storeHouse;

    public Producer(MyBlockingQueue storeHouse) {
        this.storeHouse = storeHouse;
    }

    @Override
    public void run() {
        //while (!Thread.currentThread().isInterrupted()) {
            storeHouse.put();
        //}
    }
}
