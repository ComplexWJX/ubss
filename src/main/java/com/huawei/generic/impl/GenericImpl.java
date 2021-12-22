package com.huawei.generic.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.apache.log4j.Logger;

import com.huawei.generic.Common;
import com.huawei.generic.Generic;

public abstract class GenericImpl<T, PK extends Serializable> implements Generic<T, PK> {
	private Class<?> clazz;
	private static Logger DEBUGGER = Logger.getLogger(Common.class);

	public GenericImpl() {
		DEBUGGER.info(this.getClass().getGenericSuperclass());// this表示当前正在调用方法的对象
		clazz = (Class<?>) ((ParameterizedType) (this.getClass().getGenericSuperclass())).getActualTypeArguments()[0];
		DEBUGGER.info(clazz);
	}

	@Override
	public void doSave(T t) {

	}

	@Override
	public void doDelete(PK pk) {

	}

	@Override
	public void doUpdate(T t) {

	}

	@Override
	public void doSelectById(PK pk) {

	}

}
