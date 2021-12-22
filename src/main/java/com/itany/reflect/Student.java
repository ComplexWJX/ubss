package com.itany.reflect;

import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1938359547675354415L;
	private String name;
	private Character sex;
	private Integer age;
	private Date birthday;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Character getSex() {
		return sex;
	}

	public void setSex(Character sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	//泛型方法
	public  void conver2obj(String clzz){
		
	}
	
	
}
