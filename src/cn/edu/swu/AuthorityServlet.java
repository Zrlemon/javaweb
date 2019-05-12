package cn.edu.swu;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AuthorityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String methName = request.getParameter("method");
		Method method;
		try {
			method = getClass().getMethod(methName, 
						HttpServletRequest.class,HttpServletResponse.class);
				method.invoke(this, request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();}
	}
	private UserDao userDao = new UserDao();
	public void getAuthorities(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String username = request.getParameter("username");
		User user = userDao.get(username);
		
		request.setAttribute("user",user);
		request.setAttribute("authorities", userDao.getAuthorites());
		request.getRequestDispatcher("/authority-manager.jsp").forward(request, response);
}
	public void updateAuthorities(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String [] authorities = request.getParameterValues("authority");
		List<Authority> authorityList = userDao.getAuthorites(authorities);
		userDao.update(username, authorityList);
		response.sendRedirect(request.getContextPath() + "/authority-manager.jsp");
}
	}