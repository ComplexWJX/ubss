package com.itany.collection;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import com.itany.entity.User;
import org.junit.Test;

import com.itany.entity.Cup;

public class TestHashSet {
	static Cup cup;

	static Cup cup1;

	@Test
	public void testHashcode() {
		Set<Integer> set = new HashSet<Integer>();
		set.add(11);
		set.add(22);
		set.add(33);
		for (Integer i : set) {
			System.out.println(i + ":\t" + i.hashCode());
		}
	}

	@Test
	public void testHashcodeOfObject() {
		Set<Cup> set = new HashSet<>();
		cup = new Cup();
		cup.setName("陶瓷杯");
		cup.setPrice(20.0);
		cup1 = new Cup();
		cup1.setName("保温杯");
		cup1.setPrice(30.0);
		set.add(cup);
		set.add(cup1);
		for (Cup cup : set) {
			System.out.println(cup+":\t"+"hashcode:"+cup.hashCode());
		}
	}

	/**
	 * TreeSet有序的原理是因为放入其中的元素要实现Comparable接口,然后调用TreeMap的compare方法
	 */
	@Test
	public void testTreeSet() {
		User user1 = new User();
		User user2 = new User();
		User user3 = new User();
		Set<User>set=new TreeSet<>();
		set.add(user1);
		set.add(user2);
		set.add(user3);
		for (User user : set) {
			System.out.println(user);
		}
	}
}
