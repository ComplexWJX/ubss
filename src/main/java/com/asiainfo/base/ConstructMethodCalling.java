package com.asiainfo.base;

public class ConstructMethodCalling {
	int var;

	ConstructMethodCalling(Integer var) {
		System.out.println("Integer");
		this.var = var;
	}

	ConstructMethodCalling(double var) {
		System.out.println("double");
		this.var = (int) var;
	}

	ConstructMethodCalling(int var) {
		this("hello");
	}

	ConstructMethodCalling(String s) {
		this();// 调用当前类无参构造器
		System.out.println(s);
	}

	ConstructMethodCalling() {
		System.out.println("construct()");
	}

	public static void main(String[] args) {
		// 数值类型参数默认找最匹配的类型，如果没有向上转型查找
		ConstructMethodCalling t = new ConstructMethodCalling(5);
	}
}
