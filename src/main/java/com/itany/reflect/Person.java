package com.itany.reflect;

public class Person {
private static Person p;
private Person() {
	// TODO Auto-generated constructor stub
 }
public static Person getInstance(){
	if(p==null){
		p=new Person();
	}
	return p;
}
void test(){
	System.out.println("我爱你ლ(′◉❥◉｀ლ)");
}

}
