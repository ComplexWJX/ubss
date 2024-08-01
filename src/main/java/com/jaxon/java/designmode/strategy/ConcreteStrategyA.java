package com.jaxon.java.designmode.strategy;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: WJX
 * @Date: 2021/06/28/10:34
 * @Description:
 */
public class ConcreteStrategyA extends Strategy {
    @Override
    public void doProcess() {
        System.out.println("execute strategy A");
    }

    @Override
    public boolean isSupport() {
        return false;
    }
}
