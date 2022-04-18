package com.asiainfo.java.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * @author WJX
 * @title: LambdaTest
 * @projectName ubss
 * @description: TODO
 * @date 2020/4/26 0026
 */
public class LambdaTest {

    @Test
    public void test1(){
        for (int i = 0; i < 50; i++) {
            num();
        }
    }

    @Test
    public void test2(){
        testLambda();
    }

    private static void num(){
        Random random = new Random();
        // 绘制字符
        StringBuilder strCode = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(random.nextInt(10));
            strCode.append(rand);
        }
        System.out.println(strCode);
    }

    private void testLambda(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        CompletableFuture.supplyAsync(()->{
            int total=0;
            for (Integer i:list){
                if(i==2){
                    continue;
                }
                System.out.println(i);
                total +=i;
            }
            return total;
        }).thenApply(r->{
            System.out.println(r);
            return null;
        });
    }
}
