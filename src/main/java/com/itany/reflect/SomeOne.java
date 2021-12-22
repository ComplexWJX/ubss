package com.itany.reflect;

import java.io.Serializable;

public class SomeOne implements Serializable {

	private static final long serialVersionUID = 5729696081325805142L;
	private String name;
	private double salary;
	private String address;
	private char gender;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

}
