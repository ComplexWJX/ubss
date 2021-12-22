package com.itany.javabase;

public class Base
{
    static int j = 0;
    
    public static void main(String[] args)
    {
        int i = 0;
        int b = i < 10 ? i : methodA(4);
        System.out.println(b);
    }
    
    static int methodA(int k)
    {
        return j += k;
    }
    
    static int methodB(int k)
    {
        return j += k;
    }
}
