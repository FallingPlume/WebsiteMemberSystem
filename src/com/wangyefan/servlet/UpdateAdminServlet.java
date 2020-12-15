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
 * Servlet implementation class UpdateAdminServlet
 */
@WebServlet("/UpdateAdminServlet")
public class UpdateAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//���Ȩ��
		AdminUser logingAdmin = (AdminUser) request.getSession().getAttribute("loginAdmin");
		if(logingAdmin.getAdminLevel() != 1) {
			response.sendRedirect(request.getContextPath() + "/AdminList.jsp?error=cantUpdate");
			return;
		}
		
		String id = request.getParameter("id");
		AdminUserDao adao = new AdminUserDao();
		AdminUser admin = adao.selectAdminById(Integer.parseInt(id));
		request.getSession().setAttribute("updateAdmin", admin);
		response.sendRedirect(request.getContextPath() + "/UpdateAdmin.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//���ñ���
		request.setCharacterEncoding("utf-8");
		
		//��ȡ����
		AdminUserDao adao = new AdminUserDao();
		String id = request.getParameter("id");
		String adminAccount = request.getParameter("adminAccount");
		String adminName = request.getParameter("adminName");
		String adminPwd = request.getParameter("adminPwd");
		String adminLevel = request.getParameter("adminLevel");
		
		//System.out.println("id:" + id);
		//System.out.println("adminName:" + adminName);
		//System.out.println("adminAccount:" + adminAccount);
		//System.out.println("adminPwd:" + adminPwd);
		//System.out.println("adminLevel:" + adminLevel);
		
		//�û����Ƿ��ظ�
		if(adao.isHasAccount(adminAccount, Integer.parseInt(id))) {
			response.sendRedirect(request.getContextPath() + "/UpdateAdmin.jsp?error=isHas");
			return;
		}
		
		//ִ���޸�
		String sql = "update adminUser set adminName = ?, adminAccount = ?, adminPwd = ?, adminLevel = ? where id = ?";
		int count = DBHelper.insertDeleteUpdate(sql, new Object[] {adminName, adminAccount, adminPwd, adminLevel, id});
		if(count > 0) {
			response.sendRedirect(request.getContextPath() + "/AdminListServlet");
		}else {
			response.sendRedirect(request.getContextPath() + "/AdminList.jsp?error=yes");
		}
		
	}

}
