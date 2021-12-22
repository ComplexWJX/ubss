package com.itany.staticc;

public class Static
{
    private static int i;
    
    private int j;
    
    public static void main(String[] args)
    {
        // new Main().methodA();
        new Static().methodB();
    }
    
    // 类的方法
    static void method()
    {
    }
    
    // 普通对象的方法A
    void methodA()
    {
        int x = 10;
        i = x;
        j = 5;
        System.out.println(i);
    }
    
    // 普通对象方法B
    void methodB()
    {
        methodA();
        int k = i;
        int m = j;
        System.out.println(k);
        System.out.println(m);
    }
}
