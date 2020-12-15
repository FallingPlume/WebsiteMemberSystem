package com.wangyefan.model;

public class Members {
	
	private int uid;
	private String memberAccount;
	private String memberPwd;
	private String memberName;
	private int memberLevel;
	private int memberType;
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getMemberAccount() {
		return memberAccount;
	}
	public void setMemberAccount(String memberAccount) {
		this.memberAccount = memberAccount;
	}
	public String getMemberPwd() {
		return memberPwd;
	}
	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public int getMemberLevel() {
		return memberLevel;
	}
	public void setMemberLevel(int memberLevel) {
		this.memberLevel = memberLevel;
	}
	public int getMemberType() {
		return memberType;
	}
	public void setMemberType(int memberType) {
		this.memberType = memberType;
	}
	
	@Override
	public String toString() {
		return "Members [uid=" + uid + ", memberAccount=" + memberAccount + ", memberPwd=" + memberPwd + ", memberName="
				+ memberName + ", memberLevel=" + memberLevel + ", memberType=" + memberType + "]";
	}
	
	

}
