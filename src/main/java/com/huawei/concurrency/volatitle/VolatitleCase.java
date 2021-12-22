package com.huawei.concurrency.volatitle;

/**
 * @author Koala
 * @description
 * @date 2020/2/23 0023
 */
public class VolatitleCase {

    volatile boolean isSleep;

    void countSheep(){
        while (!isSleep){
            System.out.println("it is "+isSleep+",not sleep..");
        }
        System.out.println("it is "+isSleep+",i am sleeping");
    }

    void makeSleep(){
        isSleep = true;
    }

    public static void main(String[] args) {
        VolatitleCase obj = new VolatitleCase();
        new Thread(()-> obj.countSheep()).start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                obj.makeSleep();
                System.out.println("count is:"+i);
            }
        }).start();
    }
}
