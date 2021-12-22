package com.itany.reflect;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class Main {
public static void main(String[] args) {
	//System.out.println(Math.round(-11.5));
   Father f=new Son();
   //f.show();
   Son s=(Son)f;
   Map<String, String>map=new HashMap<String, String>();
   map.put("father", "爸爸");
   map.put("mother", "妈妈");
   Set<Map<String, String>>friends=new HashSet<Map<String,String>>();
   friends.add(map);
   s.setFriends(friends);
   s.setAge(29);
   s.setName("小明");
   System.out.println(s.getFriends());
   //s.show();
   Person.getInstance().test();
}
@Test
public void showReflect(){
	new Son().showReflect();
}
@Test
public void showReflect1(){
	new Father<SomeOne, Integer>().getParams(new SomeOne());
}

}
