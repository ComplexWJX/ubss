package com.jaxon.web.entity;

import java.math.BigDecimal;

/**
 * @author Koala
 * @description
 * @date 2020/3/19 0019
 */
public class User {
    private Integer userId;
    private String username;
    private String pwd;
    private BigDecimal coins;

    public User() {
    }

    public User(Integer userId, String username, BigDecimal coins) {
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

    public BigDecimal getCoins() {
        return coins;
    }

    public void setCoins(BigDecimal coins) {
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
