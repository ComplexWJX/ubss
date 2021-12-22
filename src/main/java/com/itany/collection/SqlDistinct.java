package com.itany.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator 查找两个数组相同元素,模拟sql语句中in的原理
 */
public class SqlDistinct {

	public static void main(String[] args) {
		int a[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
		int b[] = { 1, 2, 3, 9, 11, 10 };
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < a.length; j++) {
				if (b[i] == a[j]) {
					result.add(b[i]);
					break;
				}
			}
		}
		System.out.println(result);
	}
}
