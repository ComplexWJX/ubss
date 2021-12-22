package com.itany.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.itany.entity.User;

public class Demo1
{
    
    @Test
    public void test1()
    {
        Connection conn = null;
        PreparedStatement ps = null;
        try
        {
            try
            {
                Class.forName("oracle.jdbc.driver.OracleDriver");
            }
            catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }
            String url = "jdbc:oracle:thin:@localhost:1521:orc";
            String user = "sns_005";
            String password = "abc";
            conn = DriverManager.getConnection(url, user, password);
            ps = conn.prepareStatement("select * from t_user");
            ResultSet rs = ps.executeQuery();
            User u = new User();
            while (rs.next())
            {
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setPassword(rs.getString(3));
            }
            System.out.println(u);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                ps.close();
                conn.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            
        }
    }
}
