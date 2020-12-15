package com.wangyefan.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangyefan.dao.MembersDao;
import com.wangyefan.model.Members;

/**
 * Servlet implementation class SearchMemberServlet
 */
@WebServlet("/SearchMemberServlet")
public class SearchMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//设置编码
		request.setCharacterEncoding("utf-8");
		
		//获取数据
		String uid = request.getParameter("searchId");
		String name = request.getParameter("searchName");
		String level = request.getParameter("searchLevel");
		String type = request.getParameter("searchType");
		MembersDao mdao = new MembersDao();
		
		//执行查询
		HashMap<String, String> map = new HashMap<String, String>();
		if(!"".equals(uid)) {
			map.put("uid", uid);
		}
		if(!"".equals(name)) {
			map.put("memberName", name);
		}
		if(!"".equals(level)) {
			map.put("memberLevel", level);
		}
		if(!"".equals(type)) {
			map.put("memberType", type);
		}
		ArrayList<Members> memberList = mdao.selectSearchMember(map);
		
		request.getSession().setAttribute("memberList", memberList);
		response.sendRedirect(request.getContextPath() + "/MemberList.jsp");
		
	}

}
