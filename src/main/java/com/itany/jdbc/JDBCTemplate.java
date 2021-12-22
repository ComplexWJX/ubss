package com.itany.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCTemplate {
	private static JDBCTemplate jdbcTemplate;

	private JDBCTemplate() {
	}

	public static JDBCTemplate getInstance() {
		if (jdbcTemplate == null) {
			jdbcTemplate = new JDBCTemplate();
		}
		return jdbcTemplate;
	}

	public static void main(String[] args) {
		String sql = "insert into stu values (?,?,?)";
		int random = (int) Math.random() * 10;
		JDBCTemplate.getInstance().insert(sql, random);
	}

	private void insert(String sql, int random) {
		Connection con = Demo2.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "tom" + random);
			ps.setInt(2, 1);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
