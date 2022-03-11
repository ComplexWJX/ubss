package com.asiainfo.java.designmode.proxy;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: WJX
 * @Date: 2021/06/27/12:30
 * @Description:
 */
public class ProxyTest {
    public static void main(String[] args) {
        Subject subject = new Proxy(new RealSubject());
        subject.request();
        subject.request();
    }
}
