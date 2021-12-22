package com.itany.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Son extends Father<Son, Integer> {
	private String[] hobbies;
	private Set<?> friends;

	public void setFriends(Set<?> friends) {
		this.friends = friends;
	}

	public Set<?> getFriends() {
		return friends; 
	}

	public void showReflect() {
		Class<? extends Son> clazz = new Son().getClass();
		System.out.println(clazz.getName());
		// 父类中public的属性
		System.out.println(Arrays.toString(clazz.getFields()));
		// 当前类的属性
		System.out.println(Arrays.toString(clazz.getDeclaredFields()));
		System.out.println(clazz.getSuperclass());
		Type type = clazz.getGenericSuperclass();
		System.out.println(type);
		// 向下转
		ParameterizedType p = (ParameterizedType) type;
		Class<?> z = (Class<?>) p.getActualTypeArguments()[0];
		System.out.println(z);
	}

	public <E> List<E> show(E e) {
		return null;

	};
}
