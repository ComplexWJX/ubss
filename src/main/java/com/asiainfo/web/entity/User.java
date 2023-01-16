package com.asiainfo.web.entity;

/**
 * @author Koala
 * @description
 * @date 2020/3/19 0019
 */
public class User {
    private Integer userId;
    private String username;
    private String pwd;
    private int coins;

    public User() {
    }

    public User(Integer userId, String username, int coins) {
        this.userId = userId;
        this.username = username;
        this.coins = coins;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                ", coins=" + coins +
                '}';
    }

//    @Override
//    public int compareTo(User that) {
//        return this.getUserId().compareTo(that.getUserId());
//    }

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
