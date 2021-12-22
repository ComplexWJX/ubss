package com.itany.spring.bean;

import org.springframework.beans.factory.annotation.Autowired;

import com.itany.entity.Cup;

public class SpringBean {
	@Autowired
	private Cup cup;

	public SpringBean() {
	}

	public String getCupName() {
		return cup.getName();
	}
}
