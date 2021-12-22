package com.asiainfo.base;

import java.util.HashMap;

/**
 * 代码块和静态代码块何时执行? 在创建该类对象的时候都会执行，且static修饰的只会执行一次 但是普通代码块在加载类字节码的时候不会被调用到
 */
public class ClassLoadOrder
{
    {
        System.out.println("代码块");

    }
    static
    {
        System.out.println("静态代码块");

    }

    public static void main(String[] args)
    {
        // Test1 test1 = new Test1();
        // Test1 test12=new Test1();
        // Test1.class.getClass();//只会调用静态代码块,static是属于类的
        //Date.class.getClass();
        HashMap<Object,Object> hashMap = new HashMap<>(new HashMap<>(1));
    }
}
