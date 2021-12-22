package com.itany.spring.ioc;

import java.util.Calendar;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ioc1 {
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("com/itany/spring/ioc/applicationContext.xml");
		Calendar calendar = (Calendar)ac.getBean("calendar");
		System.out.println(calendar);
		Date date = (Date) ac.getBean("date");
		System.out.println(date);
	}
}
