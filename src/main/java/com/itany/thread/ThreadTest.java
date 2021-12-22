package com.itany.thread;

import org.junit.Test;

public class ThreadTest {

	@Test
	public void test() {
		Mythread mythread = new Mythread();
		mythread.start();
		for (int i = 0; i < 20; i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// 普通类匿名子类实例
	@Test
	public void testAnonymous() {
		new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					System.out.println(i);
				}
			}

		}.start();
	}

	// 接口匿名子类
	@Test
	public void testAnnoymous1() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					System.out.println(i);
				}
			}
		}).start();

	}

}

class Mythread extends Thread {
	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			System.out.println("myThread:" + i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}