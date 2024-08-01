package com.jaxon.java.base;

import com.jaxon.web.entity.User;

/**
 * [一句话描述类功能]
 *
 * @author rukawa
 * Created on 2023/03/15 9:41 by rukawa
 */
public class PointReferenceFoo {

    public static void main(String[] args) {
        User u1 = new User();
        u1.setUsername("tom");
        // u2的引用指向u1第一次的堆内存地址
        User u2 = u1;
        System.out.println(u1.hashCode());
        u1 = new User();
        //u1.setUsername("mike");
        System.out.println(u1.hashCode());
        System.out.println(u2.hashCode());
    }
}
