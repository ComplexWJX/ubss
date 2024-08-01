package com.jaxon.java.designmode.decorate;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: WJX
 * @Date: 2021/06/17/18:48
 * @Description:
 */
public abstract class AbstractDecorator implements Component {
    protected Component component;

    public AbstractDecorator(Component component) {
        //子类super调用时，this指向子类实例
        this.component = component;
    }

    @Override
    public void pay() {
        component.pay();
    }
}
