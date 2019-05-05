package cn.edu.swu.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.swu.dao.AdminDAO;
import cn.edu.swu.dao.impl.AdminDAOJdbcImpl;



public class LoginServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;
	private AdminDAO adminDAO =  new AdminDAOJdbcImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String name = req.getParameter("name");
		String pass = req.getParameter("pass");
		HttpSession session = req.getSession();
		ServletContext application = this.getServletContext();

		try {
			long cout = adminDAO.AdminLogin(name, pass);
			if( cout >0) {
					session.setAttribute(application.getInitParameter("userSessionKey"), name);
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

