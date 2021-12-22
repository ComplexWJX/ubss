package com.itany.javabase;

/**
 * 静态代码块和普通代码块的执行顺序 静态代码块只会被执行一次
 */
public class CodeOrder
{
    // 静态变量
    // static String str=s();
    static
    {
        System.out.println("静态代码块"); // 1
    }
    
    // 静态方法
    static String s()
    {
        System.out.println("静态变量"); // 2
        return "abc";
    }
    
    // 代码块
    {
        System.out.println("代码块"); // 3
    }
    
    // 变量
    int i = res();
    
    // 普通方法
    int res()
    {
        System.out.println("变量"); // 4
        return 10;
    }
    
    // 构造方法
    public CodeOrder()
    {
        System.out.println("构造方法"); // 5
    }
    
    // 普通方法
    void a()
    {
        System.out.println("a()");
    }
    
    public static void main(String[] args)
    {
        new CodeOrder();
        new CodeOrder();
    }
}
