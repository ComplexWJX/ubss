package com.itany.arithmetic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

public class Test4 {
	public static void main(String[] args) {
		// int arr[]={1,2,3,5};
		String color[] = new String[] { "red", "yellow", "green", "blue", "black" };
		List<String> lst = new ArrayList<String>();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			int index = random.nextInt(color.length);
			lst.add(color[index]);
		}
		System.out.println(lst);
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < lst.size(); i++) {
			if (map.containsKey(lst.get(i))) {
				Integer count = map.get(lst.get(i));
				map.put(lst.get(i), count + 1);
			} else {
				map.put(lst.get(i), 1);
			}
		}

		// 遍历map集合取出结果

		Set<Entry<String, Integer>> entrySet = map.entrySet();
		for (Entry<String, Integer> entry : entrySet) {
			System.out.println(entry.getKey() + "出现次数:" + entry.getValue());
		}
	}
}
