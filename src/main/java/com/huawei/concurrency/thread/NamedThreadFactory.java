package com.huawei.concurrency.thread;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class NamedThreadFactory implements ThreadFactory {

	private String tname;
	private Thread thread;
	private ThreadFactory defaultThreadFactory = Executors.defaultThreadFactory();
	private UncaughtExceptionHandler uncaughtExceptionHandler;

	public NamedThreadFactory(String tname, UncaughtExceptionHandler uncaughtExceptionHandler) {
		this.tname = tname;
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
		thread.setName(tname);
		return thread;

	}

}
