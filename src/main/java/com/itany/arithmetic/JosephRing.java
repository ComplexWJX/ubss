package com.itany.arithmetic;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * @author koala
 * 约瑟夫环的研究
 */
public class JosephRing {
	// TODO
	public static void main(String[] args) {
		int[] a = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		List<Integer> lst = new ArrayList<Integer>();
		for (int i = 0; i < a.length; i++) {
			lst.add(a[i]);
		}
		List<Integer> f = f(lst);
		System.out.println(f);
	}

	static List<Integer> f(List<Integer> lst) {
		for (int i = 0; i < lst.size(); i += 2) {
			lst.remove(lst.get(i));
		}
		System.out.println(lst);
		if (lst.size() >= 2) {
			f(lst);
		}
		// System.out.println(lst);
		return lst;
	}

	@Test
	public void test1() {
		// int i=0;
		// while(i<10){
		// System.out.println(i);
		// i++;
		// }

		for (int j = 0; j < 5;) {
			System.out.println(j++);
		}
	}

	public void test() {
	}
}
