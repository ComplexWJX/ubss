package com.jaxon.java.concurrency.task;

import java.util.concurrent.CountDownLatch;

public class Worker implements Runnable {

	// TODO CountDownLatch和Semaphore的使用
	private CountDownLatch latch = new CountDownLatch(1);

	@Override
	public void run() {
		latch.countDown();
	}

}
