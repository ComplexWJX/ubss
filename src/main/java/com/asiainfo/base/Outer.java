package com.asiainfo.base;

/**
 * @author WJX
 * @date 2020-1-31
 * @description 内部类
 */
public class Outer {
	static {
		//only initialize once
		System.out.println("static block");
	}
	{
		//always call when call construct method
		System.out.println("common block");
	}
	public Outer() {
		System.out.println("Outer.construct");
		new Inner();
	}
	class Inner{
		private String  test;
		/*private Inner() {
			System.out.println("Outer.Inner");
		}*/
	}
	public static void main(String[] args) {
		Outer test8 = new Outer();
//		test8 = new Outer();
	}
}
