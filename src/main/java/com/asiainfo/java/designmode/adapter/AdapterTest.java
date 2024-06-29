package com.asiainfo.java.designmode.adapter;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: WJX
 * @Date: 2021/06/27/12:17
 * @Description:
 */
public class AdapterTest {
    public static void main(String[] args) {
        Target target = new Adapter(new Adaptee());
        target.request();
    }

    @Test
    public void testMultiImpl() {
        IFunctionA function = new FunctionImpl();
        function.functionB().funcB();
    }
}
