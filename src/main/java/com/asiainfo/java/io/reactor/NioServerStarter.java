package com.asiainfo.java.io.reactor;

/**
 * @Author rukawa
 * @Create 2022/9/12 0012 14:22
 * @Description todo
 * @Version V1.0
 */
public class NioServerStarter {
    public static void main(String[] args) {
        // 单线程reactor
        new Thread(new SingleThreadReactorServer()).start();

        // 多线程reactor
        new MultiThreadReactorServer().startServer();
    }
}
