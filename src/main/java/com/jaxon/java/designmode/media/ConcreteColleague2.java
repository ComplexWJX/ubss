package com.jaxon.java.designmode.media;

/**
 * @ClassName ConcreteColleague1
 * @Description TODO
 * @Author QQ
 * @Date Create in 2021/4/11 21:37
 * @Version 1.0
 */
public class ConcreteColleague2 extends Colleague {
    @Override
    void send() {
        System.out.println("colleague2 send");
        mediator.relay(this);
    }

    @Override
    void receive() {
        System.out.println("colleague2 receive");
    }
}
