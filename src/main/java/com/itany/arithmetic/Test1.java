package com.itany.arithmetic;

/**
 * 递归
 */
public class Test1 {
	public static void main(String[] args) {
		long f = f(100);
		System.out.println(f);
	}

	static long f(int n) {
		if (n <= 1) {
			return 1;
		}
		return n + f(n - 1);
	}
}
