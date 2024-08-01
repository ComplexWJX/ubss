package com.jaxon.java.designmode.singleton;

public class Singleton
{
    //对象实例共享
    private static volatile Singleton instance;
    
    private  Singleton()
    {
    }
    
    /**
     * 线程安全的单例模式
     * @return
     */
    public static Singleton getInstance(){
        if(instance == null){
            synchronized (Singleton.class)
            {
                if(instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
