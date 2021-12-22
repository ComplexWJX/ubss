package com.itany.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

public class Demo3 {

	/**
	 * oracle数据库
	 * */
	public static void main(String[] args) {
		Connection conn = getConnection();
		String sql="select * from t_area";
		String sql1="insert into t_stu values(?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
//			批处理
			PreparedStatement pst =conn.prepareStatement(sql1);
			for (int i=0;i<10;i++) {
			   pst.setInt(1, i); 
			   pst.setString(2, "tom"+i); 
			   pst.setInt(3, i);
			   pst.addBatch();
			}
			int[]rows=pst.executeBatch();
			for (int i : rows) {
				System.out.println(i);
			}
//			ResultSet rs = ps.executeQuery();
//			while(rs.next()){
//				System.out.println(rs.getString("name"));
//			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
//	调用存储过程
	@Test
	public void test(){
		Connection conn = getConnection();
		try {
			CallableStatement call = conn.prepareCall("call p()");
			call.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
//	获取连接
	static Connection getConnection(){
		Connection conn=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url="jdbc:oracle:thin:@localhost:1521:orc";
			String user="sns_005";
			String password="abc";
			try {
				conn=DriverManager.getConnection(url, user, password);
				return conn;
			} catch (SQLException e) {
				e.printStackTrace();
			}
					
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
