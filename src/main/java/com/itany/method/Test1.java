package com.itany.method;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.Proxy;

import org.junit.Test;

import com.itany.method.impl.LoginImpl;


public class Test1 {

	public static void main(String[] args) {
		testProxy("mike","123");
	}
	
//	测试
	@Test
	public  void method(){
		Login login=LoginImpl.getInstance();
		login.login("tom", "abc");
	}
	
	
//	代理
	public static void testProxy(String username,String pass){
		Login login=(Login)Proxy.newProxyInstance(
				LoginImpl.class.getClassLoader(),
				LoginImpl.class.getInterfaces(),
				new InvocationHandler() {
					
					@Override
					public Object invoke(Object proxy, Method method, Object[] args)
							throws Throwable {
						
						return method.invoke(LoginImpl.getInstance(), args);
					}
				});
		login.login(username, pass);
	}
}
