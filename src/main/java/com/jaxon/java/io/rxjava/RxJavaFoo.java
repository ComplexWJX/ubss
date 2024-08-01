package com.jaxon.java.io.rxjava;

import cn.hutool.core.collection.ListUtil;
import com.alibaba.fastjson.JSONObject;
import com.jaxon.web.entity.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Emitter;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func3;
import rx.observables.SyncOnSubscribe;
import rx.schedulers.Schedulers;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @author WJX
 * @version 1.0
 * @date 2024/03/21/15:11
 * @description
 */
public class RxJavaFoo {

    private static final Logger LOGGER = LoggerFactory.getLogger(RxJavaFoo.class);

    ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(20));

    private final User userInfo = new User();

    public static void main(String[] args) {
        RxJavaFoo rxJavaFoo = new RxJavaFoo();
        rxJavaFoo.testRxJava();
        rxJavaFoo.merge();
        rxJavaFoo.zip();
        rxJavaFoo.just();
    }

    private void just() {
        Observable.just("a", "b", "c", "d").subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError");
            }

            @Override
            public void onNext(String s) {
                try {
                    if ("a".equals(s) || "c".equals(s)) {
                        System.out.println("==========");
                        Thread.sleep(3000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("onNext: " + s);
            }
        });
    }

    private void zip() {
        // use zip
        // 使用zip并行执行所有任务，并在所有任务完成时合并结果
        Observable<String> task1 = Observable.just("String1").subscribeOn(Schedulers.io());
        Observable<Integer> task2 = Observable.just(1).subscribeOn(Schedulers.io());
        Observable<Double> task3 = Observable.just(2.0).subscribeOn(Schedulers.io());
        Observable.zip(task1, task2, task3, new Func3<String, Integer, Double, String>() {
                    @Override
                    public String call(String s, Integer i, Double d) {
                        // 合并结果的逻辑
                        return "Task 1 Result: " + s + ", Task 2 Result: " + i + ", Task 3 Result: " + d;
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println("succuss:" +  s);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }

    private void merge() {
        // use merge
        for (Observable observable : observables()) {
            Observable.merge(observable).toBlocking().subscribe(x -> {
                System.out.println("one task end.");
            });
        }

        Observable.merge(observables()).toBlocking().subscribe(x -> {
            System.out.println("one task end.");
        });


        Observable.merge(observables()).toBlocking().subscribe(new Subscriber<User>() {
            @Override
            public void onCompleted() {
                System.out.println("userInfo:" + JSONObject.toJSONString(userInfo));
                System.out.println("all tasks completed.");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(User userInfo) {
                System.out.println("userInfo:" + userInfo);
            }
        });
    }

    private List<Observable<User>> observables() {

//        User userInfo = new User();

        // 假设有三个异步任务，分别在三个单独的Observables中表示
        Observable<User> task1 = Observable.fromCallable(() -> {
            // 执行任务1的代码
            Thread.sleep(2000); // 模拟耗时操作
            System.out.println("task1 end");
            userInfo.setUsername("aa:bb:cc:dd:ee:ff");
            return userInfo;
        }).subscribeOn(Schedulers.io());

        Observable<User> task2 = Observable.fromCallable(() -> {
            // 执行任务2的代码
            Thread.sleep(3500); // 模拟耗时操作
            System.out.println("task2 end");
            userInfo.setPwd("123456");
            return userInfo;
        }).subscribeOn(Schedulers.io());

        Observable<User> task3 = Observable.fromCallable(() -> {
            // 执行任务3的代码
            Thread.sleep(1000); // 模拟耗时操作
            System.out.println("task3 end");
            userInfo.setUserId(1);
            return userInfo;
        }).subscribeOn(Schedulers.io());

        return ListUtil.toList(task1, task2, task3);
    }

    @Test
    public void testRxJava() {

        Observable.just("a", "b", "c").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });

        Observable<String> observable = Observable.create(new Action1<Emitter<String>>() {
            @Override
            public void call(Emitter<String> emitter) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    LOGGER.error("error:{}", e.getMessage(), e);
                }
                emitter.onNext("hello,");
                emitter.onNext("nice");
                emitter.onNext("a new day!");
//                DevReq devReq = new DevReq();
//                devReq.setDevMac("");
//                List<TabDevBasicInfoModel> modelList = tabuserInfoMapper.queryuserInfoList(devReq);
//                emitter.onNext(JSONArray.toJSONString(modelList));
            }
        }, Emitter.BackpressureMode.LATEST);

        observable
                .observeOn(Schedulers.from(executor))
                .subscribeOn(Schedulers.from(executor))
                .unsubscribeOn(Schedulers.from(executor))
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("observer get next message:" + s);
                    }
                });

        observable
//                .subscribeOn(Schedulers.from(executor)) // 指定subscribe()发生在哪个线程，否则默认在当前线程，与后续代码顺序执行
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println("action subscribe message:" + s);
                    }
                });

        Observable<String> observable1 = Observable.create(subscriber -> {
            subscriber.onNext("hello,");
            subscriber.onNext("nice");
            subscriber.onNext("a new day!");
        });

        Observable<String> observable2 = Observable.create(new SyncOnSubscribe<String, String>() {
            @Override
            protected String generateState() {
                return null;
            }

            @Override
            protected String next(String state, Observer<? super String> observer) {
                return null;
            }
        });

        System.out.println("end testRxJava");

//        try {
//            System.in.read();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    /**
     * todo 复现redis lettuce客户端rejectedExecutionException
     */
    @Test
    public void testConcurrentSetRedis() {
        /*
        * ulimit -u 2784 限制线程数
        *
        * */
        for (int i = 0; i < 2700; i++) {
//            threadPoolExecutor.execute(() -> {
//                long start = System.currentTimeMillis();
//                redisService.setCacheObject("test" + System.currentTimeMillis(), "test" + System.currentTimeMillis());
//                long end = System.currentTimeMillis();
//                System.out.println("set key 耗时：" + (end - start) + " ms");
//                try {
//                    Thread.sleep(500);
//                } catch (Exception e) {
//                    LOGGER.error("error:{}", e.getMessage(), e);
//                }
//            });
            final int idx = i;
            new Thread(() -> {
                try {
                    Thread.sleep(5 * 1000);
                } catch (Exception e) {
                    LOGGER.error("error:{}", e.getMessage(), e);
                }
                long start = System.currentTimeMillis();
                // todo
//                redisService.setCacheObject("test" + System.currentTimeMillis(), "test" + System.currentTimeMillis());
                long end = System.currentTimeMillis();
                System.out.println("set key "+ idx +" 耗时：" + (end - start) + " ms");
            }).start();
        }

        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("redis 处理完成");
    }

}
