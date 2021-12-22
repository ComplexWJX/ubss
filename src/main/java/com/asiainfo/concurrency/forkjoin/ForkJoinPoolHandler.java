package com.asiainfo.concurrency.forkjoin;

/**
 * @author WJX
 * @title: ForkJoinPoolHandler
 * @projectName ubss
 * @description:
 * 只有通过execute()方法提交任务，才能将它抛出的异常交给未捕获异常处理器。
 * 而通过submit()方法提交的任务，无论是抛出未检查异常还是已检查异常，都将被认为是任务返回状态的一部分。
 * 如果一个由submit()方法提交的任务由于抛出了异常而结束，该异常将被Future.get()封装在ExecutionException中重新抛出
 * @date 2020/6/3 0003
 */
public class ForkJoinPoolHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("catch uncaughtException:"+e.getMessage());
    }

}
