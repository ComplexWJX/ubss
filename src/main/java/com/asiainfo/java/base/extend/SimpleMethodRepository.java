package com.asiainfo.java.base.extend;

import java.util.Arrays;

/**
 * @author WJX
 */
public class SimpleMethodRepository extends AbstractMethodRepository {

	public static void main(String[] args) {
		String str = "ab,12,sda,";
		// String[] strs = Test9.stringSplit(str);
		String[] strs = SimpleMethodRepository.stringSplit(str);
		String[] strs1 = SimpleMethodRepository.method(str);
		System.out.println(Arrays.toString(strs));
		System.out.println(Arrays.toString(strs1));
		new SimpleMethodRepository().methodA();

	}


	@Override
	public void methodA() {
		System.out.println("Test9.methodA()");
	}

	/**
	 *  静态的方法可以被继承，但是不能重写。如果父类中有一个静态的方法，
	 *  子类也有一个与其方法名，参数类型，参数个数都一样的方法，
	 *  并且也有static关键字修饰 ，那么该子类的方法会把原来继承过来的父类的方法隐藏，而不是重写。
	 */
	protected static String[] stringSplit(String str) {
		String reg = ",";
		String[] strs = str.split(reg);
		return strs;
	}

	protected static String[] method(String str) {
		String reg = "\\d+";
		String[] strs = str.split(reg);
		return strs;
	}
}
