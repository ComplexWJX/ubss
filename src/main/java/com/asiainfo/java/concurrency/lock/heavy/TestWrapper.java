package com.asiainfo.java.concurrency.lock.heavy;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author WJX
 * @title: TestWrapper
 * @projectName ubss
 * @description: TODO
 * @date 2020/8/7 0007
 */
public class TestWrapper {
    public static void main(String[] args) {
        /**
         对于Integer var = ?  在-128至127范围内的赋值，Integer对象是在IntegerCache.cache产生，
         会复用已有对象，这个区间内的Integer值可以直接使用==进行判断，
         但是这个区间之外的所有数据，都会在堆上产生，并不会复用已有对象，这是一个大坑，推荐使用equals方法进行判断
         */
        Integer num1 = 127; //调用
        Integer num2 = 127;
        Integer num3 = 128;
        Integer num4 = 128;
        System.out.println(num1==num2); //true
        System.out.println(num3==num4); //false
        System.out.println(Integer.MAX_VALUE);
        TestWrapper wrapper1 = new TestWrapper();
        TestWrapper wrapper2 = new TestWrapper();
        System.out.println(wrapper1.equals(wrapper2));
    }

    void method() {
        LocalDateTime.now().atZone(ZoneId.systemDefault());
    }

}
