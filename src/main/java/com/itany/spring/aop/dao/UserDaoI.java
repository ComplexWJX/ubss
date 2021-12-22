package com.itany.spring.aop.dao;

public interface UserDaoI {
/**
 * 接口中只包含公共抽象方法，可以省略public abstract关键字
 * */
	public  abstract void login();
	public void logout();
}
