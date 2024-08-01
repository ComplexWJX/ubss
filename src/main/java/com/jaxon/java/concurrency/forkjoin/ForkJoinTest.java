package com.jaxon.java.concurrency.forkjoin;

import com.jaxon.java.concurrency.forkjoin.task.MyFjTask1;
import com.jaxon.java.concurrency.thread.MyThreadFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author WJX
 * @title: ForkJoinTest
 * @projectName ubss
 * @description: TODO
 * @date 2020/4/26 0026
 */
public class ForkJoinTest {

    private Logger logger = LoggerFactory.getLogger(ForkJoinTest.class);

    @Test
    public void testFj() {
        try {
            forkJob();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void forkJob() throws Exception {
        List<String> list1 = new ArrayList<>();
        //synchronizedList解决并发情况下list add错误
        List<String> resultList = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < 10; i++) {
            list1.add("2020-06-03 14:00:0" + i);
//            list1.add(i+"");
        }
        list1.add("2020-06-03 14:00:90");
        long start = System.currentTimeMillis();

        CountDownLatch countDownLatch = new CountDownLatch(list1.size());

        /*
         * ForkJoinPool实现
         */
        Resolver resolver = new Resolver(list1, resultList);
        MyThreadFactory threadFactory = new MyThreadFactory();
        ForkJoinPoolHandler handler = new ForkJoinPoolHandler();
        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors(), threadFactory, handler, false);
        forkJoinPool.execute(new MyFjTask1(0, list1.size(), countDownLatch, resolver));
//        countDownLatch.await();
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(2, TimeUnit.SECONDS);
        logger.info("forkJoinPool shutdown");
        Collections.sort(resultList);
        System.out.println(resultList);

        /*
         * ThreadPoolExecutor实现
         */
        //有界队列
        LinkedBlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>(2);
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(4,
                4,
                30,
                TimeUnit.SECONDS,
                blockingQueue,
                r -> {
                    Thread t = new Thread(r);
                    t.setUncaughtExceptionHandler((thread, e) -> {
                        System.out.println("runtime exception");
                    });
                    return t;
                }, (runnable, executor) -> {
            System.out.println("remove task:" + runnable);
            executor.remove(runnable);
        });

//        poolExecutor.execute(()->{
//            throw new RuntimeException("error occur.");
//        });

        System.out.println("cost:" + (System.currentTimeMillis() - start) + "ms");
    }
}
