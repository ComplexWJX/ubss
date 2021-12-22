package com.itany.spring.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itany.spring.bean.SpringBean;

public class ioc2 {

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"com/itany/spring/ioc/applicationContext.xml");
		SpringBean springBean=(SpringBean) ac.getBean("springbean");
		System.out.println(springBean);
		String cupName = springBean.getCupName();
		System.out.println(cupName);
	}
}
