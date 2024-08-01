package com.jaxon.java.concurrency.thread;

import com.jaxon.java.concurrency.forkjoin.ForkJoinPoolHandler;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;

/**
 * @author WJX
 * @title: MyThreadFactory
 * @projectName ubss
 * @description: TODO
 * @date 2020/6/3 0003
 */
public class MyThreadFactory implements ForkJoinPool.ForkJoinWorkerThreadFactory {

    private final ForkJoinPoolHandler handler = new ForkJoinPoolHandler();

    @Override
    public ForkJoinWorkerThread newThread(ForkJoinPool pool) {
        ForkJoinPoolThread forkJoinPoolThread = new ForkJoinPoolThread(pool);
        forkJoinPoolThread.setUncaughtExceptionHandler(handler);
        return forkJoinPoolThread;
    }
}
