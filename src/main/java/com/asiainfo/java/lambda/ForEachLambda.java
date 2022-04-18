package com.asiainfo.java.lambda;


import com.asiainfo.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @author WJX
 * @title: GroupByTest
 * @projectName ubss
 * @description: TODO
 * @date 2021/11/10 0010
 */
public class ForEachLambda {
    public static void main(String[] args) {
        User user1 = new User(1, "tom", 1);
        User user2 = new User(2, "wade", 2);
        User user3 = new User(3, "mike", 3);
        User user4 = new User(4, "jack", 4);
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
//        userList.forEach(consumerWithIndex((item, index) -> {
//            System.out.println(index);
//            System.out.println(item);
//        }));

        userList.forEach(loopWithIndex(new MyBiConsumer() {
            @Override
            public void accept(Object obj, int index) {
                System.out.println(index);
                System.out.println(obj);
            }
        }));
    }

    private static  <T> Consumer<T> consumerWithIndex(BiConsumer<T, Integer> consumer) {
        class Obj {
            private int i;
        }
        Obj obj = new Obj();
        return t -> {
            int index = obj.i++;
            consumer.accept(t, index);
        };
    }

    private static Consumer loopWithIndex (MyBiConsumer biConsumer){
        class Obj {
            private int i;
        }
        Obj obj = new Obj();
        return c -> {
            int index = obj.i++;
            biConsumer.accept(c, index);
        };
    }

    interface MyBiConsumer {
        void accept (Object obj, int index);
    }
}
