package com.asiainfo.entity;

import java.util.Objects;

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
}
