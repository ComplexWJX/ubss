package com.itany.spring.aop.impl;

import com.itany.spring.aop.dao.UserDao;
import com.itany.spring.aop.dao.UserDaoI;

public class UserDaoImpl extends UserDao implements UserDaoI{

	@Override
	public void login() {
		System.out.println("UserDaoImpl.login()");
	}

	@Override
	public void logout() {
		System.out.println("UserDaoImpl.logout()");
	}

	
}
