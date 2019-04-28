package cn.edu.swu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.swu.dao.AdminDAO;
import cn.edu.swu.dao.impl.AdminDAOJdbcImpl;
import cn.edu.swu.domain.Admin;


public class LoginServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;
	private AdminDAO adminDAO =  new AdminDAOJdbcImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String name = req.getParameter("name");
		String pass = req.getParameter("pass");

		try {
			long cout = adminDAO.AdminLogin(name, pass);
			if( cout >0) {
				Cookie cookie = new Cookie("user", name);
				cookie.setMaxAge(180);
				resp.addCookie(cookie);
				resp.sendRedirect("index.jsp");
			}
			else {
				resp.sendRedirect("loginfail.jsp");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
