package com.itany.opp;

public class Person {
private Integer id;
private String name;
private String gender;
private String interest;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public String getInterest() {
	return interest;
}
public void setInterest(String interest) {
	this.interest = interest;
}
@Override
public String toString() {
	return "Person [id=" + id + ", name=" + name + ", gender=" + gender
			+ ", interest=" + interest + "]";
}

}
