package com.asiainfo.java.concurrency.thread;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class NamedThreadFactory implements ThreadFactory {
	private final Random random = new Random(10);
	private String tName;
	private Thread thread;
	private ThreadFactory defaultThreadFactory = Executors.defaultThreadFactory();
	private UncaughtExceptionHandler uncaughtExceptionHandler;

	public NamedThreadFactory(String tName, UncaughtExceptionHandler uncaughtExceptionHandler) {
		this.tName = tName;
		this.uncaughtExceptionHandler = uncaughtExceptionHandler;
	}

	@Override
	public Thread newThread(Runnable r) {
		if (defaultThreadFactory != null) {
			thread = defaultThreadFactory.newThread(r);
		} else {
			thread = new Thread(r);
		}
		thread.setUncaughtExceptionHandler(uncaughtExceptionHandler);
		thread.setName(tName + random.nextInt());
		return thread;

	}

}
