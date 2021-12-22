package com.huawei.generic;

import com.huawei.generic.impl.GenericImpl;
import com.itany.reflect.Student;

public class AnonymousFoo {
	public static void main(String[] args) {
//		new GenricImpl<Student, Integer>() {
//		};
		new Common<Student>(){};//匿名类在编译时会生成一个真实类型，继承Common<Student>
	}
}
