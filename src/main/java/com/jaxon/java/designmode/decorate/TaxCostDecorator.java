package com.jaxon.java.designmode.decorate;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: WJX
 * @Date: 2021/06/17/18:51
 * @Description:
 */
public class TaxCostDecorator extends AbstractDecorator {
    public TaxCostDecorator(Component component) {
        super(component);
    }

    @Override
    public void pay() {
        taxPay();
        component.pay();
//        super.pay();
    }

    private void taxPay () {
        System.out.println("pay $20 for gov tax.");
    }
}
