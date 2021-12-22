package com.asiainfo.designmode.strategy;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: WJX
 * @Date: 2021/06/28/10:32
 * @Description:
 */
public abstract class Strategy {
    public Strategy() {
    }

    public abstract void doProcess();

    public abstract boolean isSupport();
}
