package com.fwq.car.utils;

import java.beans.PropertyVetoException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import com.mchange.v2.c3p0.ComboPooledDataSource;


public class DaoUtils {
	public static final ComboPooledDataSource dataSource = new ComboPooledDataSource();

	private static String url;
	private static String user;
	private static String password;
	private static String driver;
	static {
		try {
			// 创建Properties集合类
			Properties pro = new Properties();
			// 获取src路径下文件，使用ClassLoader类加载器
			ClassLoader classLoader = DaoUtils.class.getClassLoader();
			// URL定位了文件的绝对路径
			URL res = classLoader.getResource("jdbc.properties");
			// 获取字符串路径
			String path = res.getPath();
			// 读取文件
			pro.load(new FileReader(path));
			// 给静态变量赋值
			url = pro.getProperty("url");
			user = pro.getProperty("user");
			password = pro.getProperty("password");
			driver = pro.getProperty("driver");
			// 注册驱动
			Class.forName(driver);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
	//获取连接对象
	public static Connection openConn() throws PropertyVetoException {
		dataSource.setDriverClass(driver);
		dataSource.setJdbcUrl(url);
		dataSource.setUser(user);
		dataSource.setPassword(password);
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	//关闭连接
	public static void closeAll(Connection conn,Statement st, ResultSet rs)
	{
		try {
			if(conn !=null)
				conn.close();
			if(st!=null)
				st.close();
			if(rs!=null)
				rs.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
