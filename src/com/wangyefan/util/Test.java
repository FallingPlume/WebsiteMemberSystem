package com.wangyefan.util;

import java.util.ArrayList;
import java.util.HashMap;

import com.wangyefan.dao.MembersDao;
import com.wangyefan.model.Members;

public class Test {

	public static void main(String[] args) {
		
		MembersDao dao = new MembersDao();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("uid", "1");
		map.put("memberName", "уехЩ");
		map.put("MemberLevel", "2");
		map.put("memberType", "1");
		ArrayList<Members> members = dao.selectSearchMember(map);
		System.out.println(members);
		
	}
	
}
