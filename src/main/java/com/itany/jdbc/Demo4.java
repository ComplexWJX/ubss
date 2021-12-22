package com.itany.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.itany.opp.Person;

public class Demo4 {
	List<Person>lst;
@Test
public void testGetConnection(){
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/studb?useUnicode=true",
				"root", "123456");
		PreparedStatement ps=new ConnectDatabase().getPs(conn);
		ps.setString(1, "女");
	     ResultSet rs = ps.executeQuery();
	     lst=new ArrayList<Person>();
	     while(rs.next()){
	    	 Person p=new Person();
	    p.setId(rs.getInt("id"));
	    p.setName( rs.getString("name"));
	    p.setGender( rs.getString("gender"));
	    p.setInterest( rs.getString("interest"));
	    lst.add(p);
	     }
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	for (Person p : lst) {
		System.out.println(p);
	}
	
}
	
}
class ConnectDatabase{

	public PreparedStatement  getPs(Connection conn){
		String sql="select * from person where gender=?";
		try {
			PreparedStatement prepareStatement = conn.prepareStatement(sql);
			return prepareStatement;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
}
	
	
}