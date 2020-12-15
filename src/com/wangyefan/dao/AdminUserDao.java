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
	 * ͨ��id��ѯ����Ա
	 * @param id ����Աid
	 * @return ����Ա����
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
	 * ͨ���˺ź������ѯ����Ա����
	 * @param adminName ����Ա�˺�
	 * @param adminPwd ����Ա����
	 * @return ����Ա����
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
	 * ��ѯ���й���Ա
	 * @return ���й���Ա��list����
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
	 * �����ж��˺��Ƿ��ظ�����id��
	 * @param adminAccount ����Ա�˺�
	 * @param id ����Աid
	 * @return true��false
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
	 * �����ж��˺��Ƿ��ظ�����id��
	 * @param adminAccount ����Ա�˺�
	 * @return true��false
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
