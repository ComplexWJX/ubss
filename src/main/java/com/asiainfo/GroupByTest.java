package com.asiainfo;


import com.asiainfo.entity.User;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author WJX
 * @title: GroupByTest
 * @projectName ubss
 * @description: TODO
 * @date 2021/11/10 0010
 */
public class GroupByTest {
    public static void main(String[] args) {
        User user1 = new User(1, "tom", 1);
        User user2 = new User(1, "tom", 2);
        User user3 = new User(2, "mike", 5);
        User user4 = new User(3, "jack", 8);
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);

        List<User> userListAfterGroupBy = new ArrayList<>();
        Map<Integer, List<User>> groupBy = userList.stream().collect(Collectors.groupingBy(User::getUserId));
        for (Integer id : groupBy.keySet()) {
            long sum = groupBy.get(id).stream().collect(Collectors.summarizingInt(User::getCoins)).getSum();
            User user = groupBy.get(id).get(0);
            User newUser = new User();
            BeanUtils.copyProperties(user, newUser);
            newUser.setCoins((int)sum);
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
