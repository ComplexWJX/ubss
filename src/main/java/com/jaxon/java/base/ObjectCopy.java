package com.jaxon.java.base;

import com.jaxon.web.entity.User;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Koala
 * @description
 * @date 2020/3/19 0019
 */
public class ObjectCopy {
    public static void main(String[] args) {
        /*User user = new User();
        user.setUsername("tom");
        user.setPwd("123");
        convert(user);
        System.out.println(user);

        int a =10;
        plus(a);
        System.out.println(a);*/

        test1();
    }

    private static void convert(User user) {
        user.setUserId(1232);
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("username","mike");
//        jsonObject.put("pwd","111");
//        jsonObject.put("userId",2);
//        user = JSONObject.parseObject(jsonObject.toJSONString(),User.class); //引用改变
    }

    private static void plus(int num) {
        num++;
    }

    private static void test1() {
        Map<String, Object> map = new HashMap<>();
        map.put("num", 1);
        map = new HashMap<>();
        map.put("appId", 22);
//        map.remove("num");
        System.out.println(map);
    }
}
