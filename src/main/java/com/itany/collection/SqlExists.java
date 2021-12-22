package com.itany.collection;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Administrator 查找两个数组相同元素,模拟sql语句中exists的原理
 */
public class SqlExists {
	public static void main(String[] args) {
		Set<Integer> cache = new HashSet<>();
		int a[] = { 1, 2, 3, 4, 5, 6, 7, 8 };
		int b[] = { 1, 2, 3, 9, 11, 10 };
		for (int i = 0; i < b.length; i++) {
			cache.add(b[i]);
		}
	}
}
