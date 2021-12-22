package com.itany.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import org.junit.Test;

public class TestJavaBeanFields {
	private static Class<?> entityClazz;
	private final static String CLASSNAME = "com.itany.reflect.Student";
	
	{
		try {
			entityClazz = Class.forName(CLASSNAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getFields() {
		// 这样取出的属性包括了没有get/set方法的属性
		Field[] fields = entityClazz.getDeclaredFields();
		for (Field field : fields) {
			System.out.println(field);
		}
	}

	@Test
	public void autoExeSetter() {
		// 通过get方法去取
		Method[] methods = entityClazz.getDeclaredMethods();
		for (Method method : methods) {
			if (!method.getName().startsWith("get")) {
				Type[] types = method.getGenericParameterTypes();
				for (Type type : types) {
					try {
						//方法参数的Class对象
						Class<?> argclzz = (Class<?>) (type);
						try {
							method.invoke(entityClazz.newInstance(), argclzz.newInstance());// TODO 反射参数传入
						} catch (InstantiationException e) {
							e.printStackTrace();
						}
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
					}
				}

			}

		}

	}

}
