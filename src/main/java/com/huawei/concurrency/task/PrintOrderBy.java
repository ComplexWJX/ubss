package com.huawei.concurrency.task;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Koala
 * @description
 * @date 2020/3/26 0026
 */
public class PrintOrderBy {

    private Semaphore semaphoreA = new Semaphore(1);
    private Semaphore semaphoreB = new Semaphore(0);
    private Semaphore semaphoreC = new Semaphore(0);

    private AtomicInteger count = new AtomicInteger(0);

    private void printA() throws InterruptedException{
        while (count.get() < 10){
            semaphoreA.acquire();
            System.out.print("A");
            count.incrementAndGet();
            semaphoreB.release();
        }
    }
    private void printB() throws InterruptedException{
        while (count.get() < 10){
            semaphoreB.acquire();
            System.out.print("B");
            semaphoreC.release();
        }
    }
    private void printC() throws InterruptedException{
       while (count.get() < 10){
           semaphoreC.acquire();
           System.out.print("C");
           System.out.print("|");
           semaphoreA.release();
       }
        System.out.println(" ");
    }

    public static void main(String[] args) {
        PrintOrderBy foo = new PrintOrderBy();
        Thread t1 = new Thread(() -> {
            try {
                foo.printA();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                foo.printB();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t3 = new Thread(() -> {
            try {
                foo.printC();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
        t3.start();
    }
}
