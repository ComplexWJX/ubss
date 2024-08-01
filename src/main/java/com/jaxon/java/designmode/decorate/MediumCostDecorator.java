package com.jaxon.java.designmode.decorate;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: WJX
 * @Date: 2021/06/17/18:51
 * @Description:
 */
public class MediumCostDecorator extends AbstractDecorator {
    public MediumCostDecorator(Component component) {
        super(component);
    }

    @Override
    public void pay() {
        mediumPay();
        component.pay();
        super.pay();
    }

    private void mediumPay () {
        System.out.println("pay $10 for medium.");
    }
}
