package com.jaxon.java.concurrency.volatitle;

/**
 * @author Koala
 * @description
 * @date 2020/2/23 0023
 */
public class VolatileCase {

    volatile boolean isSleep;
    void countSheep() {

        while (!isSleep) {
            /*
            * [System.out.println] 是 synchronized方法，会强制同步主内存，所以加了下面这句，
            * 即使isSleep不设置为volatile，也会使得线程从主内存中重新读取isSleep的值。
            * 测试volatile线程可见性时，循环中不应使用同步方法
            * @ see https://www.zhihu.com/question/39458585
            * */
//            System.out.println(Thread.currentThread().getName() + ":it is " + isSleep + ",not sleep..");
        }
        System.out.println(Thread.currentThread().getName() + ":it is " + isSleep + ",i am sleeping =====================");
    }

    void makeSleep() {
        isSleep = true;
    }

    public static void main(String[] args) {
        VolatileCase obj = new VolatileCase();
        for (int i = 0; i < 19; i++) {
            new Thread(() -> {
                obj.countSheep();
            }, "thread-" + i).start();
        }

        new Thread(() -> obj.makeSleep()).start();
    }
}
