package com.huawei.spring.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IOCTest {

		public static void main(String[] args)
        {
		    ApplicationContext ac2= new ClassPathXmlApplicationContext("classpath:spring-aop.xml");
		    ApplicationContext ac1 = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	        //JdbcTemplate jdbcTemplate = (JdbcTemplate)ac.getBean("localJdbcTempSessionFactory");
	        //LocalJdbcTempSessionFactory localJdbcTempSessionFactory = (LocalJdbcTempSessionFactory) ac.getBean("&localJdbcTempSessionFactory");
	        //System.out.println(jdbcTemplate.getClass());
	        //System.out.println(localJdbcTempSessionFactory.getClass());
        }

}
