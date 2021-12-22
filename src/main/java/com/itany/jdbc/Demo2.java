package com.itany.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Assert;
import org.junit.Test;

import com.alibaba.druid.util.Base64;
import com.itany.entity.Product;
import com.itany.utils.MD5Util;

/**
 * @author WJX forkjoin 2017-7-26
 */
public class Demo2
{
    /**
     * 连接池连接数据库dbcp
     */
    private static DataSource dataSource;

    private static ThreadLocal<Connection> threadLocal =
        new ThreadLocal<Connection>();
    static
    {

        // 读取配置文件
        InputStream in = Demo2.class.getClassLoader()
            .getResourceAsStream("datasource.properties");
        Properties p = new Properties();
        try
        {
            p.load(in);
            String username = (String)p.get("username");
            String password = (String)p.get("password");
            String url = (String)p.get("url");
            String driverClassName = (String)p.get("driverClassName");
            try
            {
                checkConnect(username, password, url, driverClassName);
            }
            catch (DataAccessException e)
            {
                e.printStackTrace();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        // 获取数据源
        try
        {
            dataSource = BasicDataSourceFactory.createDataSource(p);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // 查询
    @Test
    public void test()
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        try
        {
            // 获取连接
            conn = getConnection();
            // 开启事务
            conn.setAutoCommit(false);
            // 根据连接获取预处理对象
            ps = conn.prepareStatement("select * from user");
            // 执行数据库查询操作
            rs = ps.executeQuery();
            // 提交事务
            conn.commit();
            // 映射
            List<Product> lst = new ArrayList<Product>();
            while (rs.next())
            {
                Product product = new Product();
                product.setProductName(rs.getString(2));
                product.setOwnedBank(rs.getString("ownedBank"));
                lst.add(product);
            }
            for (Product product : lst)
            {
                System.out.println("产品名称:" + product.getProductName()
                    + "\t所属银行:" + product.getOwnedBank());
            }
        }
        catch (SQLException e)
        {
            try
            {
                conn.rollback();
            }
            catch (SQLException e1)
            {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                rs.close();
                ps.close();
                conn.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    private static void checkConnect(String username, String password,
        String url, String driverClassName)
        throws DataAccessException
    {
        if (username == null)
        {
            throw new DataAccessException("用户名错误");
        }
        else if (password == null)
        {
            throw new DataAccessException("密码错误");
        }
        else if (url == null)
        {
            throw new DataAccessException("url错误");
        }
        else if (driverClassName == null)
        {
            throw new DataAccessException("驱动名错误");
        }
        else
        {
            return;
        }
    }

    // 修改
    @Test
    public void test1()
    {
        PreparedStatement prs = null;
        Connection conn = null;
        try
        {
            conn = getConnection();
            // 设置手动提交
            conn.setAutoCommit(false);
            // String sql = "update sysuser set loginName=? where id=?";
            String sql = "insert into wq_user values(default,?,?,?,?,?)";
            prs = conn.prepareStatement(sql);
            // Assert.assertNotNull(ps);
            prs.setString(1, "mike");
            // prs.setInt(2, 1);
            prs.setString(2, MD5Util.md5Encode("123456"));
            prs.setString(3, "18751325615");
            prs.setInt(4, 1);
            prs.setInt(5, 6000);
            //int rowAffects = prs.executeUpdate(sql);
            prs.execute();
            conn.commit();
            //System.out.println("影响行数:" + rowAffects);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                prs.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

    }

    // 从本地线程中获取连接
    public static Connection getConnection()
    {
        Connection conn = null;
        try
        {
            conn = threadLocal.get();
            if (conn == null)
            {
                conn = dataSource.getConnection();
                threadLocal.set(conn);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return conn;
    }

}
