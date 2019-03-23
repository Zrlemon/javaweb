package cn.edu.swu;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;

public class Login extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp){
		//System.out.println(req.getContextPath());
		String user = req.getParameter("user");
		String pass = req.getParameter("pass");
		PrintWriter out = null;
		try{
			out = resp.getWriter();
			out.println(new String(user.getBytes("ISO-8859-1"), "UTF-8"));
		} catch(Exception e) {
			out.println("encoding error");
		}
		out.println("Hello: " + user);
		out.println("This is a test");
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp){
		doPost(req, resp);
	}
}
