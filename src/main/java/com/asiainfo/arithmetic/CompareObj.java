package com.asiainfo.arithmetic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;

import com.asiainfo.entity.EHSObject;
import com.asiainfo.entity.Student;

public class CompareObj {
	private static Logger DEBUGGER =Logger.getLogger("com.huawei");

	public static void main(String[] args) {
		List<Student> list = getData();
		Collections.sort(list, new Comparator<Student>() {

			// 自定义排序规则，首先按名字ASCII码顺序排列，如果名字相同按年龄升序排，如果年龄相同再按成绩升序排列
			public int compare(Student o1, Student o2) {
				return (o1.getName().compareTo(o2.getName()) !=0) ? o1.getName()
						.compareTo(o2.getName()) : o1.getAge().compareTo(
						o2.getAge()) != 0 ? o1.getAge().compareTo(o2.getAge())
						: o1.getScore().compareTo(o2.getScore());
			}

		});
//		DEBUGGER.info(list);

		compareObj(new Student());
		compareObj(new EHSObject());
	}

	public static void compareObj(Object obj){

		//DEBUGGER.info(obj.getClass().getName()+" instance of EHSObject "+ (obj instanceof EHSObject));
		//DEBUGGER.info(obj.getClass().getName()+" instance of Student "+ (obj instanceof Student));
		DEBUGGER.info("EHSObject isInstance("+obj.getClass().getName()+"),"+ (EHSObject.class.isInstance(obj)));
		DEBUGGER.info("Student isInstance("+obj.getClass().getName()+"),"+ (Student.class.isInstance(obj)));

		//Student.class.isInstance(obj)等效于obj instanceof Student
	}


	private static List<Student> getData() {
		List<Student> list = new ArrayList<Student>();
		Student u1 = new Student();
		u1.setName("tom");
		u1.setAge(22);
		u1.setScore(90);

		Student u2 = new Student();
		u2.setName("tom");
		u2.setAge(22);
		u2.setScore(89);

		Student u3 = new Student();
		u3.setName("yike");
		u3.setAge(22);
		u3.setScore(86);

		list.add(u1);
		list.add(u2);
		list.add(u3);
		return list;
	}
}
