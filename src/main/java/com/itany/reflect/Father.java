package com.itany.reflect;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Administrator
 * 
 * */
public class Father<T,P> {
	public Integer age;
	public String name;
	
 public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
public void getParams(T t){
	 Field[]fields =t.getClass().getDeclaredFields();
	for(Field f:fields){
      System.out.println(f.getName());
	}
	}

//方法的泛型
public <E> List<E> show(E e){
	List<String>list=new ArrayList<String>();
	list.add("床前明月光");
	list.add("疑是地上霜");
	System.out.println("show方法");
	return (List<E>) list;
	
}
}
