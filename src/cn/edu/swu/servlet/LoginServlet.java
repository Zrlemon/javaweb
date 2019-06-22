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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String name = req.getParameter("name");
		String pass = req.getParameter("pass");
		HttpSession session = req.getSession();
		ServletContext application = this.getServletContext();
		String path = null;
		System.out.println("1");
		try {
			
			long cout = adminDAO.AdminLogin(name, pass);
			System.out.println("2");
			String character = adminDAO.getcharacter(name, pass);
			System.out.println("3");
			if( cout >0) {
				System.out.println("4");
					session.setAttribute(application.getInitParameter("userSessionKey"), name);
					System.out.println("5");
					if(character.equals("超级管理员")) {
						System.out.println("6");
						//path = "/admin/index.jsp";
						path = req.getContextPath() + "/admin/index.jsp";	
						System.out.println("7");
					}
					else {
						System.out.println("8");
						//path = "/users/index.jsp";
						path = req.getContextPath() + "/users/index.jsp";	
					}	
			}
			else {
				System.out.println("9");
				//req.setAttribute("message", "用户名或密码错误，请重新输入！");
				//path = "/loginfail.jsp";
				//req.getRequestDispatcher("/login.jsp").forward(req, resp);
				path = req.getContextPath() + "/loginfail.jsp";

			}
			System.out.println("10");
			resp.sendRedirect(path);
			//req.getRequestDispatcher(path).forward(req, resp);
			System.out.println("11");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("123");
			e.printStackTrace();
			System.out.println("123");
		}
	}
}
