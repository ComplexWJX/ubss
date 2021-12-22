package com.asiainfo.base;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author WJX
 * @date 2029-1-31
 */
public abstract class AbstractMethodRepository {
	public static void main(String[] args) {
		String str = "abc123?skn21oi123..  rr";
		String reg = "\\d+";
		String s = "1234";

		Pattern p = Pattern.compile(reg);
		Matcher matcher = p.matcher(s);
		System.out.println(matcher.matches());
		String[] strs = stringSplit(str);
		System.out.println(Arrays.toString(strs));
	}

	protected static String[] stringSplit(String str) {
		String reg = "\\d+";
		String[] strs = str.split(reg);
		return strs;
	}

	// final修饰方法不能被重写
	public /* final */ void methodA() {
	}

	final void methodA(String str) {
	}

	final void methodA(String str, Integer num) {
	}
}
