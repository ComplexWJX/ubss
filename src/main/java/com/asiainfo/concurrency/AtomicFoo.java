package com.asiainfo.concurrency;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Koala
 * @description
 * @date 2020/3/18 0018
 */
public class AtomicFoo {
    public static void main(String[] args) {
        final AtomicBoolean atomicBoolean = new AtomicBoolean();
      /*  new Thread(()->{
            atomicBoolean.set(true);
        }).start();*/
        try {
            Thread.sleep(2000);
            System.out.println(atomicBoolean.get());
            throw new Exception("aaa");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

    }
}
