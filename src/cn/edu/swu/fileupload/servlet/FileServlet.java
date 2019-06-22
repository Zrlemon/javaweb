package cn.edu.swu.fileupload.servlet;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.edu.swu.dao.*;
import cn.edu.swu.dao.impl.*;
import cn.edu.swu.domain.*;




public class FileServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private FileDAO fileDAO =  new UploadFileDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("utf-8");
			//获取ServletPath
		String servletPath = request.getServletPath();
		    //去除/和.do
		System.out.println(servletPath);
		String methodName = servletPath.substring(7);
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
	
	private void query(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		System.out.println(1);
		
		String file_name = request.getParameter("file_name");
		String file_desc = request.getParameter("file_desc");
		
		
		CriteriaFile ff = new CriteriaFile(file_name, file_desc);
		System.out.println(2);
		
		//1. 调用AdminDAO 的getAll()得到Admin的集合
		List<FileUploadBean> fileUploadBeans = fileDAO.getForListWithCriteriaFiles(ff);
		System.out.println(3);
		//for(Admin admin:admins)
			//System.out.println(admin.getDate());
		//2. 把Admin的集合放入request中
		request.setAttribute("fileUploadBeans", fileUploadBeans);
		System.out.println(1);
		
		// 3. 转发页面到index.jsp
		request.getRequestDispatcher("fileupload.jsp").forward(request, response);
	}
	private void delete(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String idstr = request.getParameter("id");
		//System.out.println(idstrString);
		int id = 0;
		try {
			id = Integer.parseInt(idstr);
			fileDAO.delete(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		//System.out.println(1);
		response.sendRedirect("query.is");
	}
	private void download(HttpServletRequest request,HttpServletResponse response)throws IOException {
		String idstr = request.getParameter("id");
		int id =0;
		id = Integer.parseInt(idstr);
		FileUploadBean fileUploadBeans = fileDAO.get(id);
		String filename = fileUploadBeans.getFile_name();
		String filepath = fileUploadBeans.getFile_path();
		response.setContentType("application/x-msdownload"); 
		response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
		OutputStream out = response.getOutputStream();
		InputStream in = new FileInputStream(filepath);
		byte [] buffer = new byte[1024];
		int len = 0;
		
		while((len = in.read(buffer)) != -1){
			out.write(buffer, 0, len);
		}
		
		in.close();
	
	

	
}
}
