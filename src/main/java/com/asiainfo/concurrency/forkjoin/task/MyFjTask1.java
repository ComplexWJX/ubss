package com.asiainfo.concurrency.forkjoin.task;

import com.asiainfo.concurrency.forkjoin.Resolver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.RecursiveAction;

/**
 * @author WJX
 * @title: MyFjTask1
 * @projectName ubss
 * @description: TODO
 * @date 2020/6/1 0001
 */
public class MyFjTask1 extends RecursiveAction {

    private int start;

    private int end;

    private CountDownLatch latch;

    private Resolver resolver;

    public MyFjTask1(int start, int end, CountDownLatch latch, Resolver resolver) {
        this.start = start;
        this.end = end;
        this.latch = latch;
        this.resolver = resolver;
    }

    @Override
    protected void compute() {
        if ((end - start) <= 5) {
            resolver.solve(start, end, latch);
        } else {
            int middle = (end + start) / 2;
            MyFjTask1 left = new MyFjTask1(start, middle, latch, resolver);
            MyFjTask1 right = new MyFjTask1(middle, end, latch, resolver);
            invokeAll(left, right);
        }

    }
}
