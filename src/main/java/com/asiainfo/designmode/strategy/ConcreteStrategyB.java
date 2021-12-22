package com.asiainfo.designmode.strategy;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: WJX
 * @Date: 2021/06/28/10:34
 * @Description:
 */
public class ConcreteStrategyB extends Strategy {
    @Override
    public void doProcess() {
        System.out.println("execute strategy B");
    }

    @Override
    public boolean isSupport() {
        return true;
    }
}
