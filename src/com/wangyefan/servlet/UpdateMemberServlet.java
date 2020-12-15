package com.wangyefan.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangyefan.dao.MembersDao;
import com.wangyefan.model.Members;
import com.wangyefan.util.DBHelper;

/**
 * Servlet implementation class UpdateMemberServlet
 */
@WebServlet("/UpdateMemberServlet")
public class UpdateMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uid = request.getParameter("id");
		MembersDao mdao = new MembersDao();
		Members updateMember = mdao.selectMemberById(Integer.parseInt(uid));
		request.getSession().setAttribute("updateMember", updateMember);
		response.sendRedirect(request.getContextPath() + "/UpdateMember.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//设置编码
		request.setCharacterEncoding("utf-8");
		
		//获取数据
		MembersDao mdao = new MembersDao();
		String uid = request.getParameter("id");
		String memberName = request.getParameter("memberName");
		String memberAccount = request.getParameter("memberAccount");
		String memberPwd = request.getParameter("memberPwd");
		String memberLevel = request.getParameter("memberLevel");
		String memberType = request.getParameter("memberType");
		
		//判断账号是否重复
		if(mdao.isHasAccount(memberAccount, Integer.parseInt(uid))) {
			response.sendRedirect(request.getContextPath() + "/UpdateMember.jsp?error=isHas");
			return;
		}
		
		//判断会员等级和会员类别是否相符
		if(("0".equals(memberLevel) && !"0".equals(memberType)) || ("0".equals(memberType) && !"0".equals(memberLevel))) {
			response.sendRedirect(request.getContextPath() + "/UpdateMember.jsp?error=noMatch");
			return;
		}
		
		//执行修改
		String sql = "update members set memberName = ?, memberAccount = ?, memberPwd = ?, memberLevel = ?, memberType = ? where uid = ?";
		int count = DBHelper.insertDeleteUpdate(sql, new Object[] {memberName, memberAccount, memberPwd, memberLevel, memberType, uid});
		if(count > 0) {
			response.sendRedirect(request.getContextPath() + "/MemberListServlet");
		}else {
			response.sendRedirect(request.getContextPath() + "/MemberList.jsp?error=yes");
		}
		
	}

}
