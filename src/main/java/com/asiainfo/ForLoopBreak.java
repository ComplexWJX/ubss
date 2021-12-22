package com.asiainfo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author WJX
 * @title: ForLoopBreak
 * @projectName ubss
 * @description: TODO
 * @date 2020/7/28 0028
 */
public class ForLoopBreak {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.submit(()->{
            for (int i = 0; i < 50; i++) {
                if(i == 10){
                    continue;
                }
                System.out.println("i="+i);
            }
        });
    }
}
