package com.wangyefan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.wangyefan.model.AdminUser;
import com.wangyefan.util.DBHelper;

public class AdminUserDao {
	
	/**
	 * 通过id查询管理员
	 * @param id 管理员id
	 * @return 管理员对象
	 */
	public AdminUser selectAdminById(int id) {
		AdminUser admin = null;
		Connection con = DBHelper.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("select * from adminUser where id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				admin = new AdminUser();
				admin.setId(rs.getInt("id"));
				admin.setAdminAccount(rs.getString("adminAccount"));
				admin.setAdminName(rs.getString("adminName"));
				admin.setAdminPwd(rs.getString("adminPwd"));
				admin.setAdminLevel(rs.getInt("adminLevel"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBHelper.closeResource(con, ps, rs);
		}
		return admin;
	}
	
	/**
	 * 通过账号和密码查询管理员对象
	 * @param adminName 管理员账号
	 * @param adminPwd 管理员密码
	 * @return 管理员对象
	 */
	public AdminUser selectAdminByAccount(String adminAccount, String adminPwd) {
		AdminUser admin = null;
		Connection con = DBHelper.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("select * from adminUser where adminAccount = ? and adminPwd = ?");
			ps.setString(1, adminAccount);
			ps.setString(2, adminPwd);
			rs = ps.executeQuery();
			while(rs.next()) {
				admin = new AdminUser();
				admin.setId(rs.getInt("id"));
				admin.setAdminAccount(rs.getString("adminAccount"));
				admin.setAdminName(rs.getString("adminName"));
				admin.setAdminPwd(rs.getString("adminPwd"));
				admin.setAdminLevel(rs.getInt("adminLevel"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBHelper.closeResource(con, ps, rs);
		}
		return admin;
	}
	
	/**
	 * 查询所有管理员
	 * @return 所有管理员的list集合
	 */
	public ArrayList<AdminUser> selectAllAdmin(){
		ArrayList<AdminUser> adminList = new ArrayList<AdminUser>();
		Connection con = DBHelper.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("select * from adminUser");
			rs = ps.executeQuery();
			while(rs.next()) {
				AdminUser admin = new AdminUser();
				admin.setId(rs.getInt("id"));
				admin.setAdminAccount(rs.getString("adminAccount"));
				admin.setAdminName(rs.getString("adminName"));
				admin.setAdminPwd(rs.getString("adminPwd"));
				admin.setAdminLevel(rs.getInt("adminLevel"));
				adminList.add(admin);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBHelper.closeResource(con, ps, rs);
		}
		return adminList;
	}
	
	/**
	 * 用于判断账号是否重复（有id）
	 * @param adminAccount 管理员账号
	 * @param id 管理员id
	 * @return true或false
	 */
	public boolean isHasAccount(String adminAccount, int id) {
		boolean isHas = false;
		Connection con = DBHelper.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("select * from adminUser where adminAccount = ? and id <> ?");
			ps.setString(1, adminAccount);
			ps.setInt(2, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				isHas = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBHelper.closeResource(con, ps, rs);
		}
		return isHas;
	}
	
	/**
	 * 用于判断账号是否重复（无id）
	 * @param adminAccount 管理员账号
	 * @return true或false
	 */
	public boolean isHasAccount(String adminAccount) {
		boolean isHas = false;
		Connection con = DBHelper.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("select * from adminUser where adminAccount = ?");
			ps.setString(1, adminAccount);
			rs = ps.executeQuery();
			if(rs.next()) {
				isHas = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBHelper.closeResource(con, ps, rs);
		}
		return isHas;
	}
	
	

}
