package com.itany.thread;

public class RunnableTest {
	public static void main(String[] args) {
		MyRunable r = new MyRunable();
//		myRunable.run();
//		给线程设置名称
		Thread t1=new Thread(r,"runnable1");
		Thread t2=new Thread(r,"runnable2");
	      t1.start();
	      t2.start();
	      for (int i = 0; i < 20; i++) {
//	    	  主线程main
			System.out.println(Thread.currentThread().getName()+":"+i);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

class MyRunable implements Runnable {

	@Override
	public synchronized void run() {
		for (int i = 0; i < 20; i++) {
			System.out.println(Thread.currentThread().getName()+":"+i);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}