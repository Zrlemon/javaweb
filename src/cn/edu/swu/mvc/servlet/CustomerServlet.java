package cn.edu.swu.mvc.servlet;

import java.io.IOException;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.swu.mvc.dao.CriteriaCustomer;
import cn.edu.swu.mvc.dao.CustomerDAO;
import cn.edu.swu.mvc.dao.impl.CustomerDAOJdbcImpl;
import cn.edu.swu.mvc.domain.Customer;



public class CustomerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private CustomerDAO customerDAO =  new CustomerDAOJdbcImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			response.sendRedirect("Error.jsp");
		}
			
		}

	private void edit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String forwardPath = "/error.jsp";
		String idstr = request.getParameter("id");
		try {
			Customer customer = customerDAO.get(Integer.parseInt(idstr));
			if(customer!=null) {
			forwardPath = "/updatecustomer.jsp";
			request.setAttribute("customer",customer);
			}
		} catch (Exception e) {
		}
		request.getRequestDispatcher(forwardPath).forward(request, response);
	}
	private void update(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String oldName = request.getParameter("oldName");
		
		if(!oldName.equalsIgnoreCase(name)) {
			long count = customerDAO.getCountWithName(name);
			if(count > 0 ) {
				request.setAttribute("message", "用户名" + name + "已经被占用，请重新选择");
				request.getRequestDispatcher("/updatecustomer.jsp").forward(request, response);
				return;
			}}
			Customer customer = new Customer(name, address, phone);
			customer.setId(Integer.parseInt(id));
			customerDAO.update(customer);
			response.sendRedirect("query.do");
					
	
	}
	
	private void query(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		
		CriteriaCustomer cc = new CriteriaCustomer(name, address, phone);
		
		//1. 调用CustomerDAO 的getAll()得到Customer的集合
		List<Customer> customers = customerDAO.getForListWithCriteriaCustomer(cc);
		//2. 把Customer的集合放入request中
		request.setAttribute("customers", customers);
		// 3. 转发页面到index.jsp
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
	private void delete(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String idstrString = request.getParameter("id");
		int id = 0;
		try {
			id = Integer.parseInt(idstrString);
			customerDAO.delete(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		response.sendRedirect("query.do");
	}
	
	private void addCustomer(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//1.获取表单参数
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		//检验name是否已经被占用
		long count = customerDAO.getCountWithName(name);
		if(count > 0) {
			request.setAttribute("massage","用户名" + name + "已经被占用。请重新选择！");
			request.getRequestDispatcher("/newcustomer.jsp").forward(request, response);
			return;
		}
		//2.把表单参数封装为一个Customer 对象customer
		Customer customer = new Customer(name, address, phone);
		//3.调用customerDAO的save方法
		customerDAO.save(customer);
		//4.重定向到success.jsp

		response.sendRedirect("success.jsp");
		
	}
	
}

