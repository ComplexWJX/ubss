package com.itany.arithmetic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

public class Test2 {
	@Test
	public void test1() {
		String arr[] = new String[] { "红", "橙", "黄" };
		List<String> colors = new ArrayList<String>();
		Random random = new Random();
		for (int i = 0; i < 30; i++) {
			int num = random.nextInt(arr.length);
			colors.add(arr[num]);
		}
		System.out.println(colors);

		// 定义最多出现字符的颜色初始
		String most = "";
		// 定义连续出现的字符的出现次数
		int max = 1;
		// 初始化重复次数,最少一次
		int temp = 1;
		// 存放出现频率最高颜色，可能次数相等
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < colors.size() - 1; i++) {
			// 如果连续出现，前后两个元素相等，成对查找
			if (colors.get(i).equals(colors.get(i + 1))) {
				temp++;
				// 如果连续出现次数刷新记录
				if (temp >= max) {
					most = colors.get(i);
					max = temp;
					map.put(most, max);
				}
			}
			// 如果中断，将连续出现次数重置为1
			else {
				temp = 1;
			}
		}
		// 遍历取出结果
		Set<Entry<String, Integer>> entrySet = map.entrySet();
		for (Entry<String, Integer> entry : entrySet) {
			System.out.println(entry.getKey() + "连续出现" + entry.getValue());
		}
	}
}
