package com.wangyefan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import com.wangyefan.model.Members;
import com.wangyefan.util.DBHelper;

public class MembersDao {
	
	/**
	 * ��ѯ���л�Ա
	 * @return ���л�Ա��list����
	 */
	public ArrayList<Members> selectAllMember(){
		ArrayList<Members> memberList = new ArrayList<Members>();
		Connection con = DBHelper.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("select * from members");
			rs = ps.executeQuery();
			while(rs.next()) {
				Members member = new Members();
				member.setUid(rs.getInt("uid"));
				member.setMemberAccount(rs.getString("memberAccount"));
				member.setMemberPwd(rs.getString("memberPwd"));
				member.setMemberName(rs.getString("memberName"));
				member.setMemberLevel(rs.getInt("memberLevel"));
				member.setMemberType(rs.getInt("memberType"));
				memberList.add(member);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBHelper.closeResource(con, ps, rs);
		}
		return memberList;
	}
	
	/**
	 * ͨ��id��ѯ��Ա
	 * @param uid ��Աid
	 * @return ��Ա����
	 */
	public Members selectMemberById(int uid) {
		Members member = null;
		Connection con = DBHelper.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("select * from members where uid = ?");
			ps.setInt(1, uid);
			rs = ps.executeQuery();
			while(rs.next()) {
				member = new Members();
				member.setUid(rs.getInt("uid"));
				member.setMemberAccount(rs.getString("memberAccount"));
				member.setMemberPwd(rs.getString("memberPwd"));
				member.setMemberName(rs.getString("memberName"));
				member.setMemberLevel(rs.getInt("memberLevel"));
				member.setMemberType(rs.getInt("memberType"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBHelper.closeResource(con, ps, rs);
		}
		return member;
	}
	
	/**
	 * �����ж��˺��Ƿ��ظ�
	 * @param memberAccount ��Ա�˺�
	 * @param uid ��Աid
	 * @return true��false
	 */
	public boolean isHasAccount(String memberAccount, int uid) {
		boolean isHas = false;
		Connection con = DBHelper.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("select * from members where memberAccount = ? and uid <> ?");
			ps.setString(1, memberAccount);
			ps.setInt(2, uid);
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
	 * ������ѯ��Ա
	 * @param args ����map����
	 * @return ��Ա����
	 */
	public ArrayList<Members> selectSearchMember(HashMap<String, String> args){
		ArrayList<Members> memberList = new ArrayList<Members>();
		Connection con = DBHelper.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from members where 1 = 1";
		Set<String> keys = args.keySet();
		for (String key : keys) {
			if(key.equals("memberName")) {
				sql = sql + " and " + key + " like ?";
				continue;
			}
			sql = sql + " and " + key + " = ?";
		}
		try {
			ps = con.prepareStatement(sql);
			int count = 1;
			for (String key : keys) {
				if(key.equals("memberName")) {
					ps.setString(count, "%" + args.get(key) + "%");
					count++;
					continue;
				}
				ps.setString(count, args.get(key));
				count++;
			}
			rs = ps.executeQuery();
			while(rs.next()) {
				Members member = new Members();
				member.setUid(rs.getInt("uid"));
				member.setMemberAccount(rs.getString("memberAccount"));
				member.setMemberPwd(rs.getString("memberPwd"));
				member.setMemberName(rs.getString("memberName"));
				member.setMemberLevel(rs.getInt("memberLevel"));
				member.setMemberType(rs.getInt("memberType"));
				memberList.add(member);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBHelper.closeResource(con, ps, rs);
		}
		return memberList;
	}
	
	

}
