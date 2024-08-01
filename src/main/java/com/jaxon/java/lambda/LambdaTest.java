package com.jaxon.java.lambda;

import com.jaxon.web.entity.User;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @author WJX
 * @title: LambdaTest
 * @projectName ubss
 * @description: TODO
 * @date 2020/4/26 0026
 */
public class LambdaTest {

    @Test
    public void testSupply(){
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

    @Test
    public void testJava8Reduce() {
        List<User> userList= new ArrayList<>();
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        User user4 = new User();
        user1.setUsername("OCR01");
        user1.setCoins(new BigDecimal("0.2"));

        user1.setUsername("OCR01");
        user1.setCoins(new BigDecimal("0.5"));

        user2.setUsername("OCR02");
        user2.setCoins(new BigDecimal("0.3"));

        user3.setUsername("OCR02");
        user3.setCoins(new BigDecimal("0.8"));

        user4.setUsername("OCR03");
        user4.setCoins(null);

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);

        // todo 用对象分组求和无法处理？
//        Map<String, BigDecimal> collect = userList.parallelStream()
//                .collect(Collectors.groupingBy(User::getUsername,
//                        Collectors.reducing(new BigDecimal("0"),
//                                t->{
//                                    if (t.getCoins()==null) {
//                                        return new BigDecimal("0");
//                                    }
//                                    return t.getCoins();
//                                }, (v1, v2) -> v1.add(v2))));


        Map<String, User> collect = userList.parallelStream()
                .collect(Collectors.groupingBy(User::getUsername,
                        Collectors.reducing(new User(),
                                t->{
                                    return t;
                                }, (v1, v2) -> {
                                    if (v1.getUsername() != null) {
                                        BigDecimal add = v1.getCoins().add(v2.getCoins());
                                        v1.setCoins(add);
                                    }
                                    return v1;
                                })));

        System.out.println(collect);
    }

    @Test
    public void testGroupByAndSum() {

        User user1 = new User(1, "tom", BigDecimal.valueOf(1));
        User user2 = new User(1, "tom", BigDecimal.valueOf(2));
        User user3 = new User(2, "mike", BigDecimal.valueOf(5));
        User user4 = new User(3, "jack", BigDecimal.valueOf(8));
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);

        List<User> userListAfterGroupBy = new ArrayList<>();
        Map<Integer, List<User>> groupBy = userList.stream().collect(Collectors.groupingBy(User::getUserId));
        for (Integer id : groupBy.keySet()) {
            long sum = groupBy.get(id).stream().collect(Collectors.summarizingInt(u -> u.getCoins().intValue())).getSum();
            User user = groupBy.get(id).get(0);
            User newUser = new User();
            BeanUtils.copyProperties(user, newUser);
            newUser.setCoins(BigDecimal.valueOf(sum));
            userListAfterGroupBy.add(newUser);
        }
        for (User user : userListAfterGroupBy) {
            System.out.println(user);
        }
//        TreeMap<User, Integer> map = userList.stream().collect(Collectors.groupingBy(o -> new User(o.getUserId(), o.getUsername(), o.getCoins()), TreeMap::new, Collectors.summingInt(User::getCoins)));
//        for (User user : map.keySet()) {
//            System.out.println(user);
//        }
    }
}
