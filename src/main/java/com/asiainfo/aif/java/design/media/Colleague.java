package com.asiainfo.aif.java.design.media;

/**
 * @ClassName Colleague
 * @Description TODO
 * @Author QQ
 * @Date Create in 2021/4/11 21:30
 * @Version 1.0
 */
public abstract class Colleague {
    protected Mediator mediator;

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    abstract void send();

    abstract void receive();
}
