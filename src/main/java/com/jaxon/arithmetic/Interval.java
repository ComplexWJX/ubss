package com.jaxon.arithmetic;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Interval {
    private static int num=10;
	public static void main(String[] args) {
	   print();
	}

	static void print(){
		if(num==0){
			return;
		}
		System.out.println("第"+num+"次");
		System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		num--;
		print();
	}
}
