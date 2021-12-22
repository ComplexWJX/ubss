package com.itany.spring.aop.advice;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.aop.MethodBeforeAdvice;


public class BeforeAdviceImpl implements MethodBeforeAdvice{


	@Override
	public void before(Method method, Object[] ars, Object target)
			throws Throwable {
		System.out.println(method.getName()+"\t excute at:\t"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}

}
