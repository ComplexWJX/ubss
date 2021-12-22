package com.itany.javabase;

public class TryCatchTest
{
    
    @SuppressWarnings("finally")
    public static int get()
    {
        
        try
        {
            // System.exit(0);退出JVM， finally部分不会执行
            return 1;
        }
        finally
        {
            return 2;
        }
    }
    
    public static void main(String[] args)
    {
        System.out.println(get01());
    }
    
    // 如果try{}之中有return，不会立即返回，而是等finally之中的语句执行完了才会返回，
    // 但如果finally{}之中return了，那么try{}之中的return便不会再执行
    private static int get01()
    {
        
        try
        {
            return fun01();
        }
        finally
        {
            System.out.println(fun02());
        }
    }
    
    static int fun01()
    {
        System.out.println("fun01()");
        return 1;
    }
    
    static int fun02()
    {
        System.out.println("fun02()");
        return 2;
    }
    
}
