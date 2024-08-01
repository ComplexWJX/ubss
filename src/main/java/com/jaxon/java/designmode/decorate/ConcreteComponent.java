package com.jaxon.java.designmode.decorate;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: WJX
 * @Date: 2021/06/17/18:47
 * @Description:
 */
public class ConcreteComponent implements Component {
    @Override
    public void pay() {
        System.out.println("pay 30$");
    }
}
