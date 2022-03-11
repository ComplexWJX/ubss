package com.asiainfo.java.designmode.media;

/**
 * @ClassName Main
 * @Description TODO
 * @Author QQ
 * @Date Create in 2021/4/11 21:37
 * @Version 1.0
 */
public class Main {
    public static void main(String[] args) {
        ConcreteColleague1 colleague1 = new ConcreteColleague1();
        ConcreteColleague2 colleague2 = new ConcreteColleague2();
        ConcreteMediator mediator = new ConcreteMediator();
        mediator.register(colleague1);
        mediator.register(colleague2);
        colleague1.send();
        System.out.println("-----------------------------");
        colleague2.send();
    }
}
