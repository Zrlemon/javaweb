package cn.edu.swu.servlet;

import java.io.IOException;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.swu.dao.CriteriaAdmin;
import cn.edu.swu.dao.AdminDAO;
import cn.edu.swu.dao.impl.AdminDAOJdbcImpl;
import cn.edu.swu.domain.Admin;




public class AdminServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private AdminDAO adminDAO =  new AdminDAOJdbcImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("utf-8");
			//获取ServletPath
		String servletPath = request.getServletPath();
		    //去除/和.do
		String methodName = servletPath.substring(1);
		methodName = methodName.substring(0, methodName.length()-3);
		

		Method method;
		try {
			//利用反射获取methodName对应的方法
			method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			 //利用反射调用对应的方法
			method.invoke(this, request,response);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			response.sendRedirect("error.jsp");
		}
			
		}

	private void edit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String forwardPath = "/error.jsp";
		String idstr = request.getParameter("id");
		try {
			Admin admin = adminDAO.get(Integer.parseInt(idstr));
			//System.out.println(admin);
			if(admin!=null) {
			forwardPath = "/admin-edit.jsp";
			request.setAttribute("admin",admin);
			}
		} catch (Exception e) {
		}
		//System.out.println(forwardPath);
		//System.out.println(idstr);
		//System.out.println(1);
		request.getRequestDispatcher(forwardPath).forward(request, response);
	}
	private void update(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
		//System.out.println(1);
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String mail = request.getParameter("mail");
		String characters = request.getParameter("characters");
		String pass = request.getParameter("pass");
		String oldName = request.getParameter("oldName");
		//System.out.println(characters);
		//System.out.println(2);
		if(!oldName.equalsIgnoreCase(name)) {
			//System.out.println(3);
			long count = adminDAO.getCountWithName(name);
			if(count > 0 ) {
				//System.out.println(4);
				request.setAttribute("message", "用户名" + name + "已经被占用，请重新选择");
				request.getRequestDispatcher("admin-edit.jsp").forward(request, response);
				return;
			}}
		    //System.out.println(5);
			Admin admin = new Admin(name, phone, mail, characters, pass);
			//System.out.println(characters);
			admin.setId(Integer.parseInt(id));
			//System.out.println(characters);
			adminDAO.update(admin);
			response.sendRedirect("query.do");
					
	
	}
	
	private void query(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String mail = request.getParameter("mail");
		
		CriteriaAdmin aa = new CriteriaAdmin(name, phone, mail);
		
		//1. 调用AdminDAO 的getAll()得到Admin的集合
		List<Admin> admins = adminDAO.getForListWithCriteriaAdmins(aa);
		//for(Admin admin:admins)
			//System.out.println(admin.getDate());
		//2. 把Admin的集合放入request中
		request.setAttribute("admins", admins);
		// 3. 转发页面到index.jsp
		request.getRequestDispatcher("admin-list.jsp").forward(request, response);
	}
	private void delete(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String idstrString = request.getParameter("id");
		//System.out.println(idstrString);
		int id = 0;
		try {
			id = Integer.parseInt(idstrString);
			adminDAO.delete(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		//System.out.println(1);
		response.sendRedirect("query.do");
	}
	
	private void addAdmin(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//1.获取表单参数
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String mail = request.getParameter("mail");
		String characters = request.getParameter("character");
		//System.out.println(characters);
		String pass = request.getParameter("pass");
		//检验name是否已经被占用
		long count = adminDAO.getCountWithName(name);
		if(count > 0) {
			request.setAttribute("massage","用户名" + name + "已经被占用。请重新选择！");
			request.getRequestDispatcher("admin-add.jsp").forward(request, response);
			return;
		}
		
		//2.把表单参数封装为一个Admin 对象Admin
		Admin admin = new Admin(name, phone, mail, characters,pass);
		//3.调用AdminDAO的save方法
		adminDAO.save(admin);
		System.out.println(admin);
		//4.重定向到success.jsp
		response.sendRedirect("admin-list.jsp");
		
	}
	
}
