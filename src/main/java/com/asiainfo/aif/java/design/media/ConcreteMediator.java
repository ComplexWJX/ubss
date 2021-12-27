package com.asiainfo.aif.java.design.media;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ConcreteMediator
 * @Description TODO
 * @Author QQ
 * @Date Create in 2021/4/11 21:34
 * @Version 1.0
 */
public class ConcreteMediator extends Mediator {

    private List<Colleague> colleagues = new ArrayList<>();

    @Override
    public void register(Colleague colleague) {
        if (!colleagues.contains(colleague)) {
            colleague.setMediator(this);
            colleagues.add(colleague);
        }
    }

    @Override
    public void relay(Colleague colleague) {
        colleague.receive();
//        for (Colleague ob : colleagues) {
//            if (!ob.equals(colleague)) {
//                ((Colleague) ob).receive();
//            }
//        }
    }
}
