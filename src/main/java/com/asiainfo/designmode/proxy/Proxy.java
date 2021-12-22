package com.asiainfo.designmode.proxy;

import java.util.concurrent.Semaphore;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: WJX
 * @Date: 2021/06/27/12:25
 * @Description:
 */
public class Proxy implements Subject {
    private RealSubject realSubject;
    private final Semaphore semaphore = new Semaphore(1);

    public Proxy(RealSubject realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public void request() {
        if (semaphore.tryAcquire(1)) {
            this.realSubject.request();
//            semaphore.release(1);
        } else {
            rejectRequest();
        }
    }

    private void rejectRequest() {
        System.out.println("reject request!");
    }
}
