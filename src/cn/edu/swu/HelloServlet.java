package cn.edu.swu;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class HelloServlet implements Servlet{

	@Override
	public void destroy() {
		System.out.println("destroy..");
	}

	@Override
	public ServletConfig getServletConfig() {
		System.out.println("getServletConfig");
		return null;
	}

	@Override
	public String getServletInfo() {
		System.out.println("getServletInfo");
		return null;
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init");
		
		String user = config.getInitParameter("user");
		System.out.println("user " + user);
		
		Enumeration<String> names = config.getInitParameterNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			String vlue = config.getInitParameter(name);
			System.out.println("name " + name + ":" + vlue);	
		}
		//获取ServletContext 对象
		ServletContext servletContext = config.getServletContext();
		
		String driver = servletContext.getInitParameter("driver");
		System.out.println("driver: " + driver);
		
		Enumeration<String> names2 = servletContext.getInitParameterNames();
		while (names2.hasMoreElements()) {
			String name = names2.nextElement();
			System.out.println("--> "+ name);
		}
		
		String realPath = servletContext.getRealPath("/note.txt");
		System.out.println(realPath);
		
		String contextPath = servletContext.getContextPath();
		System.out.println(contextPath);
			try {
				ClassLoader classLoader = getClass().getClassLoader();
				InputStream is = classLoader.getResourceAsStream("jdbc.properties");
				System.out.println("1 " + is);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			try {
				InputStream is2 = servletContext.getResourceAsStream("/WEB-INF/classesjdbc.properties");
				System.out.println("2 " + is2);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		System.out.println("service");
		
	}
	

}

