package com.asiainfo.spring.ioc;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.jdbc.core.JdbcTemplate;

public class LocalJdbcTempSessionFactory implements FactoryBean<JdbcTemplate>{

	private JdbcTemplate jdbcTemplate;
	
	@Override
	public JdbcTemplate getObject() throws Exception {
		// TODO Auto-generated method stub
		return jdbcTemplate;
	}

	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return JdbcTemplate.class;
	}

	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return true;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	

}
