package com.itany.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class ListTest {
public static void main(String[] args) {
	List lst=new ArrayList<String>();
	lst.add("aa");
	lst.add(1);
	lst.add(2);
	lst.add(3);
//	无法排序
//	Collections.sort(lst);
	System.out.println(lst);
	Map map=new HashMap();
	map.put(2, "aa");
	
	Set set=new TreeSet();
	set.add(1);
	set.add("2");
	set.add("3");
	Iterator it=set.iterator();
	while(it.hasNext()){
		System.out.println(it.next());
		
	}
}
}
