package com.asiainfo.java.concurrency.lock.condition;

public class Consumor implements Runnable{
    private MyBlockingQueue storeHouse;

    public Consumor(MyBlockingQueue storeHouse) {
        this.storeHouse = storeHouse;
    }

    @Override
    public void run() {
        //while (!Thread.currentThread().isInterrupted()) {
            storeHouse.take();
        //}
    }
}
