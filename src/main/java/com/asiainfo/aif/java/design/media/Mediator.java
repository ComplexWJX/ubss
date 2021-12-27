package com.asiainfo.aif.java.design.media;

/**
 * @ClassName Mediator
 * @Description TODO
 * @Author QQ
 * @Date Create in 2021/4/11 21:30
 * @Version 1.0
 */
public abstract class Mediator {
    public abstract void register(Colleague colleague);

    public abstract void relay(Colleague colleague);
}
