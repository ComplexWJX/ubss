package com.asiainfo.designmode.adapter;

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
}
