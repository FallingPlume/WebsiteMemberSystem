package com.wangyefan.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wangyefan.dao.AdminUserDao;
import com.wangyefan.model.AdminUser;
import com.wangyefan.util.DBHelper;

/**
 * Servlet implementation class UpdateAdminInfoServlet
 */
@WebServlet("/UpdateAdminInfoServlet")
public class UpdateAdminInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAdminInfoServlet() {
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
		AdminUserDao adao = new AdminUserDao();
		String id = request.getParameter("id");
		String newAdminName = request.getParameter("newAdminName");
		String newAdminAccount = request.getParameter("newAdminAccount");
		String newAdminPwd = request.getParameter("newAdminPwd");
		
		//用户名是否重复
		if(adao.isHasAccount(newAdminAccount, Integer.parseInt(id))) {
			response.sendRedirect(request.getContextPath() + "/UpdateAdminInfo.jsp?error=isHas");
			return;
		}
		
		//执行修改
		String sql = "update adminUser set adminName = ?, adminAccount = ?, adminPwd = ? where id = ?";
		int count = DBHelper.insertDeleteUpdate(sql, new Object[] {newAdminName, newAdminAccount, newAdminPwd, id});
		if(count > 0) {
			AdminUser admin = adao.selectAdminByAccount(newAdminAccount, newAdminPwd);
			request.getSession().setAttribute("loginAdmin", admin);
			response.sendRedirect(request.getContextPath() + "/ManageTable.jsp");
			
		}else {
			response.sendRedirect(request.getContextPath() + "/UpdateAdminInfo.jsp?error=yes");
		}
		
	}

}
