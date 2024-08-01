package com.jaxon.java.concurrency;

import com.itany.entity.User;

/**
 * @Author rukawa
 * @Create 2022/9/16 0016 23:03
 * @Description todo
 * @Version V1.0
 */
public class MyFinalFoo {
    private final User user;

    public MyFinalFoo() {
        user = new User();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User u) {
        //this.user = u; //Cannot assign a value to final variable 'user'
    }

    public static void main(String[] args) {
        MyFinalFoo foo = new MyFinalFoo();
        System.out.println(foo.getUser());
    }
}
