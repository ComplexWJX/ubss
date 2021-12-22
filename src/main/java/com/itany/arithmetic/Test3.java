package com.itany.arithmetic;

import org.junit.Test;

public class Test3 {

	@Test
	public void test() {
		char a[] = new char[] { 'a', 'b', 'a' };
		String b[] = new String[] { "a", "b", new String("a") };
		System.out.println(a[0] == a[2]);
		System.out.println(b[0] == b[2]);
		System.out.println(b[0].equals(b[2]));
	}
}
