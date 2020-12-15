package com.wangyefan.model;

public class AdminUser {
	
	private int id;
	private String adminName;
	private String adminAccount;
	private String adminPwd;
	private int adminLevel;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminAccount() {
		return adminAccount;
	}
	public void setAdminAccount(String adminAccount) {
		this.adminAccount = adminAccount;
	}
	public String getAdminPwd() {
		return adminPwd;
	}
	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}
	public int getAdminLevel() {
		return adminLevel;
	}
	public void setAdminLevel(int adminLevel) {
		this.adminLevel = adminLevel;
	}
	
	@Override
	public String toString() {
		return "AdminUser [id=" + id + ", adminName=" + adminName + ", adminAccount=" + adminAccount + ", adminPwd="
				+ adminPwd + ", adminLevel=" + adminLevel + "]";
	}
	
	

}
