package com.asiainfo.java.designmode.command;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: WJX
 * @Date: 2021/06/28/09:57
 * @Description:
 */
public class ConcreteCommand extends AbstractCommand {
    public ConcreteCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void execute() {
        receiver.action();
    }
}
