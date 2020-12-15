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
 * Servlet implementation class AddAdminServlet
 */
@WebServlet("/AddAdminServlet")
public class AddAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddAdminServlet() {
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
		
		//���ñ���
		request.setCharacterEncoding("utf-8");
		
		//���Ȩ��
		AdminUser admin = (AdminUser) request.getSession().getAttribute("loginAdmin");
		if(admin.getAdminLevel() != 1) {
			response.sendRedirect(request.getContextPath() + "/AdminList.jsp?error=cantAdd");
			return;
		}
		
		//��ȡ����
		AdminUserDao adao = new AdminUserDao();
		String adminName = request.getParameter("adminName");
		String adminAccount = request.getParameter("adminAccount");
		String adminPwd = request.getParameter("adminPwd");
		String adminLevel = request.getParameter("adminLevel");
		
		//�˺��Ƿ��ظ�
		if(adao.isHasAccount(adminAccount)) {
			response.sendRedirect(request.getContextPath() + "/AddAdmin.jsp?error=isHas");
			return;
		}
		
		//ִ�����
		String sql = "insert into adminUser values(?,?,?,?)";
		int count = DBHelper.insertDeleteUpdate(sql, new Object[] {adminName, adminAccount, adminPwd, adminLevel});
		if(count > 0) {
			response.sendRedirect(request.getContextPath() + "/AdminListServlet");
		}else {
			response.sendRedirect(request.getContextPath() + "/AddAdmin.jsp?error=yes");
		}
		
	}

}
