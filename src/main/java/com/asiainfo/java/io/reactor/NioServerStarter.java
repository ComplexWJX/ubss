package com.asiainfo.java.io.reactor;

/**
 * @Author rukawa
 * @Create 2022/9/12 0012 14:22
 * @Description todo
 * @Version V1.0
 */
public class NioServerStarter {
    public static void main(String[] args) {
        new Thread(new SingleThreadReactorServer()).start();
    }
}
