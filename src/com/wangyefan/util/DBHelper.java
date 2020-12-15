package com.wangyefan.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DBHelper {
	
	private static String driver;
	private static String url;
	private static String name;
	private static String pwd;
	
	//静态加载属性配置
	static {
		Properties properties = new Properties();
		try {
			properties.load(DBHelper.class.getResourceAsStream("DBConfig.properties"));
			driver = properties.getProperty("driver");
			url = properties.getProperty("url");
			name = properties.getProperty("name");
			pwd = properties.getProperty("pwd");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取连接
	 * @return 连接对象
	 */
	public static Connection getConnection() {
		
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, name, pwd);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
		
	}
	
	/**
	 * 关闭资源
	 * @param con 连接对象
	 * @param ps 预编译语句对象
	 * @param rs 结果集对象
	 */
	public static void closeResource(Connection con, PreparedStatement ps, ResultSet rs) {
		
		try {
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
			if(con != null)
				con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 统一增删改方法
	 * @param sqlStatement sql语句
	 * @param args sql语句参数
	 * @return 影响的行数
	 */
	public static int insertDeleteUpdate(String sqlStatement, Object[] args) {
		int count = 0;
		Connection con = getConnection();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sqlStatement);
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}
			count = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResource(con, ps, null);
		}
		return count;
	}
	
	

}
