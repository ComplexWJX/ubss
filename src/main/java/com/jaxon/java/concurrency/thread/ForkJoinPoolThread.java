package com.jaxon.java.concurrency.thread;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;

/**
 * @author WJX
 * @title: ForkJoinPoolThread
 * @projectName ubss
 * @description: TODO
 * @date 2020/6/3 0003
 */
public class ForkJoinPoolThread extends ForkJoinWorkerThread {
    public ForkJoinPoolThread(ForkJoinPool pool) {
        super(pool);
    }

    @Override
    protected void onStart() {
        super.onStart();
        throw new RuntimeException("启动失败");
    }
}
