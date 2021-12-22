package com.itany.spring.aop.impl;

import com.itany.spring.aop.service.LoginService;

public class LoginServiceImp implements LoginService
{
    
    @Override
    public void login()
    {
        System.out.println("LoginServiceImp.login()");
    }
    
}
