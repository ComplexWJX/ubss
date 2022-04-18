package com.asiainfo.java.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class Ticket {
    private float price;

    private String consumer;

    /**
     * 自增自减并非原子操作，所以声明为volatile也不能保证数据的准确性
     */
    //private volatile  int total = 10;

    private AtomicInteger total = new AtomicInteger(10);

    /**
     * Thread保证了每个线程特定对象的数据共享
     */
    private final ThreadLocal<Integer> getCount = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    public int getTotal() {
        return total.get();
    }

    public int getLimit() {
        return getCount.get();
    }

    public void set() {
        total.decrementAndGet();
        getCount.set(getCount.get() + 1);
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer;
    }

}
