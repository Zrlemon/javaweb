package cn.edu.swu;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DeleteStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Id = request.getParameter("Id");
			
		StudentDao studentDao =new StudentDao();
			
		studentDao.DeleteById(Integer.parseInt(Id));
		
		request.getRequestDispatcher("./success.jsp").forward(request, response);
		
	}

}

