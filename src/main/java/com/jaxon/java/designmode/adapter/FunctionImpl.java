package com.jaxon.java.designmode.adapter;

/**
 * Created with IntelliJ IDEA.
 *
 * @author WJX
 * @version 1.0
 * @date 2024/01/03/17:11
 * @description
 */
public class FunctionImpl implements IFunctionA, IFunctionB {
    @Override
    public IFunctionB functionB() {
        return this;
    }

    @Override
    public void funcA() {
        System.out.println("func a");
    }

    @Override
    public void funcB() {
        System.out.println("func b");
    }
}
