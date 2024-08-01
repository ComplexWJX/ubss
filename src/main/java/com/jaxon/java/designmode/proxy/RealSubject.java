package com.jaxon.java.designmode.proxy;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: WJX
 * @Date: 2021/06/27/12:22
 * @Description: 代理模式和适配器模式的区别就在于被代理对象是否实现了目标接口
 */
public class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("real request");
    }
}
