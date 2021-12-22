package com.huawei.entity;

public class Ticket
{
    private float price;

    private String consumer;

    /**
     * Thread保证了每个线程特定对象的数据共享
     */
    private ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    public int getLimit()
    {
        return threadLocal.get();
    }

    public void set(){
        threadLocal.set(threadLocal.get() + 1);
    }

    public float getPrice()
    {
        return price;
    }

    public void setPrice(float price)
    {
        this.price = price;
    }

    public String getConsumer()
    {
        return consumer;
    }

    public void setConsumer(String consumer)
    {
        this.consumer = consumer;
    }

}
