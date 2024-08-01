package com.jaxon.java.designmode.strategy;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: WJX
 * @Date: 2021/06/28/10:49
 * @Description:
 */
public class StrategyTest {
    public static void main(String[] args) {
        Strategy strategyA = new ConcreteStrategyA();
        Strategy strategyB = new ConcreteStrategyB();
        new Context(strategyA).execute();
        new Context(strategyB).execute();
    }
}
