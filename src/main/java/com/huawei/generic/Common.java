package com.huawei.generic;

import java.lang.reflect.ParameterizedType;

import org.apache.log4j.Logger;

public class Common<T> {
	private Class<?> clazz;
	private static Logger DEBUGGER = Logger.getLogger(Common.class);

	public Common() {
		DEBUGGER.info(this);// 匿名类的真实类型
		clazz = (Class<?>) ((ParameterizedType) (this.getClass().getGenericSuperclass())).getActualTypeArguments()[0];
		DEBUGGER.info(clazz);
	}
}
