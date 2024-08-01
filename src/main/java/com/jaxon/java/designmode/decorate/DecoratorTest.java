package com.jaxon.java.designmode.decorate;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: WJX
 * @Date: 2021/06/17/18:55
 * @Description:
 */
public class DecoratorTest {
    @Test
    public void testDecorator () {
        ConcreteComponent component = new ConcreteComponent();
        Component decorator = new MediumCostDecorator(new TaxCostDecorator(component));
        decorator.pay();

//        new TaxCostDecorator(new MediumCostDecorator(component)).pay();

    }
}
