package com.wangyefan.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangyefan.model.AdminUser;
import com.wangyefan.util.DBHelper;

/**
 * Servlet implementation class DeleteAdminServlet
 */
@WebServlet("/DeleteAdminServlet")
public class DeleteAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//检查权限
		AdminUser admin = (AdminUser) request.getSession().getAttribute("loginAdmin");
		if(admin.getAdminLevel() != 1) {
			response.sendRedirect(request.getContextPath() + "/AdminList.jsp?error=cantDel");
			return;
		}
		
		String id = request.getParameter("id");
		//是否删除的是自己
		if(admin.getId() == Integer.parseInt(id)) {
			response.sendRedirect(request.getContextPath() + "/AdminList.jsp?error=cantDelSelf");
			return;
		}
		
		//执行删除
		int count = DBHelper.insertDeleteUpdate("delete from adminUser where id = ?", new Object[] {id});
		if(count > 0) {
			response.sendRedirect(request.getContextPath() + "/AdminListServlet");
		}else {
			response.sendRedirect(request.getContextPath() + "/AdminList.jsp?error=yes");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
