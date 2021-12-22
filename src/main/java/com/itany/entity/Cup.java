package com.itany.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class Cup implements Serializable{

	private static final long serialVersionUID = -1110360357110345848L;
	private String name;
	private Double price;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

}
