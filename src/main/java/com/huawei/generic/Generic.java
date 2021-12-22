package com.huawei.generic;

import java.io.Serializable;

public interface Generic<T, PK extends Serializable> {
	public void doSave(T t);

	public void doDelete(PK pk);

	public void doUpdate(T t);

	public void doSelectById(PK pk);

}
