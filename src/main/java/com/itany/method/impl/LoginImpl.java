package com.itany.method.impl;

import com.itany.method.Login;

public class LoginImpl implements Login{
    private static LoginImpl instance;
	private  LoginImpl() {
	}
	public static synchronized LoginImpl getInstance(){
		if(instance==null){
			instance=new LoginImpl();
		}
		return instance;
	}
	
	
	@Override
	public void login(String username, String password) {
		System.out.println(username+":"+password+"登录了....");
	}

}
