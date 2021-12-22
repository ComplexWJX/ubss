package com.itany.arithmetic;

import java.util.LinkedList;
import java.util.Queue;

public class Test6{

	public static void main(String[] args) {
		 Queue<Integer>queue=new LinkedList<Integer>();
		 for (int i = 0; i < 10; i++) {
			 queue.add(i);
		}
		 System.out.println(queue);
	}
	
}
