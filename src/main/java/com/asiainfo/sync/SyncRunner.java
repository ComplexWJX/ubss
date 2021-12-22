package com.asiainfo.sync;

import java.util.concurrent.CompletableFuture;

/**
 * @author Koala
 * @description
 * @date 2020/1/8 0008
 */
public class SyncRunner {
    private volatile boolean isStart;
    void sync() {
        if (isStart){
            //...
        }else {
            //锁()中对象
            synchronized (this){
                // io process
            }
        }
    }

    /**
     * 锁对象实例
     */
    synchronized void sync2(){

    }

    /**
     * 锁当前类的Class对象
     */
    synchronized static void sync3(){

    }

    public static void main(String[] args) {
//        CompletableFuture<String> completableFuture =
//        CompletableFuture.supplyAsync(()->"aa").thenCombineAsync(()->{return "bb"});
    }
}
