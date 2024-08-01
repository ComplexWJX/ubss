package com.jaxon.java.lambda;


import com.jaxon.web.entity.User;

import java.math.BigDecimal;
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
public class CustomizedForEach {
    public static void main(String[] args) {
        User user1 = new User(1, "tom", BigDecimal.ONE);
        User user2 = new User(2, "wade", BigDecimal.valueOf(2));
        User user3 = new User(3, "mike", BigDecimal.valueOf(3));
        User user4 = new User(4, "jack", BigDecimal.valueOf(4));
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
