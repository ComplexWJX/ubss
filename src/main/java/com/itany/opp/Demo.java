package com.itany.opp;

public class Demo {
	
	public static void main(String[] args) {
		
		new Son("");
	}
}
class Father{
	public Father(){
		System.out.println("C");
	}
	public Father(String father){
		System.out.println("B");
	}
}
class Son extends Father{
	public Son(String son){
		System.out.println("D");
	}
  }
