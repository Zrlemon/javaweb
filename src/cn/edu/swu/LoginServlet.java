package cn.edu.swu;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

public void login(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		String name = request.getParameter("name");
		User user = userDao.get(name);
		request.getSession().setAttribute("user", user);
		response.sendRedirect(request.getContextPath()+"/articles.jsp");

}
}
