package com.jaxon.java.designmode.command;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: WJX
 * @Date: 2021/06/28/09:57
 * @Description: 命令调用者
 */
public class Invoker {
    protected AbstractCommand command;

    public Invoker(AbstractCommand command) {
        this.command = command;
    }

    public void invoke(){
        System.out.println("invoke the command");
        this.command.execute();
    }
}
