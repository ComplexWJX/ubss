package com.itany.spring.aop.advice;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.springframework.aop.AfterReturningAdvice;

public class AfterAdvice implements AfterReturningAdvice{

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args,
			Object target) throws Throwable {
		System.out.println("方法名 :"+method.getName()+"\t方法返回值:"+returnValue
				+"\t方法参数:"+Arrays.toString(args)+"\t目标对象:"+target);
	}

}
