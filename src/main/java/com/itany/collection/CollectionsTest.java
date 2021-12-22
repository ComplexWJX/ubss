package com.itany.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;

public class CollectionsTest {
	@Test
	public void testList() {
		/**
		 * 1. List集合 
		 * 特点:有序,可为null,可重复
		 * */
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(3);
		list.add(2);
		list.add(6);
		list.add(5);
		// 为null不能进行冒泡
		// list.add(null);
		System.out.println(list.size());
		// for(int i=0;i<list.size();i++){
		// System.out.println(list.get(i));
		// }
		// 冒泡 升序
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = 0; j < list.size() - 1 - i; j++) {
				if (list.get(j) > list.get(j + 1)) {
					Integer temp = list.get(j);
					System.out.println(temp);
					list.set(j, list.get(j + 1));
					list.set(j + 1, temp);
				}
			}
		}
		System.out.println(list);
	}

	@Test
	public void testSet() {
		/**
		 * 2. Set集合
		 * 特点:无序、不可重复(相同只保留一个,用hashCode(),equals()方法进行比较)
		 * 可为null
		 * */

		Set<Integer> set = new HashSet<Integer>();
		set.add(1);
		set.add(1);
        set.add(2);
        set.add(2);
        set.add(2);
        set.add(null);
        System.out.println(set);

        Set<String> set1 = new HashSet<String>();
        set1.add("aa");
        set1.add("aa");
        set1.add("bb");
        set1.add("bb");
        System.out.println(set1);

	}

	@Test
	public void testMap() {
		/**
		 * 3. Map集合
		 * 特点:键无序且不可重复(相同只保留先添加的,比较hashCode和用equals()方法进行比较)
		 * 键(key)和值(value)都可为null
		 * */
//		HashMap
		 Map<String, String>hashmap=new HashMap<String, String>();
		 hashmap.put("mike", "麦克");
		 hashmap.put("tom", "汤姆");
		 hashmap.put("jack", "杰克");
		 hashmap.put("rose", "罗斯");
		 if(hashmap.containsKey("rose")){
			 hashmap.remove("rose");
		 }
		
//		 迭代器循环
		 Iterator<String>iterator=hashmap.keySet().iterator();
		 while(iterator.hasNext()){
			 String key=iterator.next();
			 System.out.println(hashmap.get(key));
		 }
//		values遍历
		 Collection<String> values = hashmap.values();
		 for (String val : values) {
			System.out.println(val);
		}
//		 entrySet
	     Set<Entry<String, String>>set=hashmap.entrySet(); 
	     for(Entry<String, String>e:set){
	    	 System.out.println(e.getKey()+"---"+e.getValue());
	     }
	
	}
}
