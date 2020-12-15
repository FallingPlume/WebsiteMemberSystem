package com.wangyefan.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wangyefan.dao.AdminUserDao;
import com.wangyefan.model.AdminUser;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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

		//…Ë÷√±‡¬Î
		request.setCharacterEncoding("utf-8");
		
		String adminAccount = request.getParameter("adminAccount");
		String adminPwd = request.getParameter("adminPwd");
		
		AdminUserDao adao = new AdminUserDao();
		AdminUser admin = adao.selectAdminByAccount(adminAccount, adminPwd);
		if(admin != null) {
			request.getSession().setAttribute("loginAdmin", admin);
			response.sendRedirect(request.getContextPath() + "/ManageTable.jsp");
			
		}else {
			response.sendRedirect(request.getContextPath() + "/index.jsp?error=yes");
		}
	
	
	}

}
