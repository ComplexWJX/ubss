package com.jaxon.java.designmode.command;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: WJX
 * @Date: 2021/06/28/10:07
 * @Description:
 */
public class Client {
    public static void main(String[] args) {
        Invoker invoker = new Invoker(new ConcreteCommand(new Receiver()));
        invoker.invoke();
    }
}
