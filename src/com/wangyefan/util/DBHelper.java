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
	
	//��̬������������
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
	 * ��ȡ����
	 * @return ���Ӷ���
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
	 * �ر���Դ
	 * @param con ���Ӷ���
	 * @param ps Ԥ����������
	 * @param rs ���������
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
	 * ͳһ��ɾ�ķ���
	 * @param sqlStatement sql���
	 * @param args sql������
	 * @return Ӱ�������
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
