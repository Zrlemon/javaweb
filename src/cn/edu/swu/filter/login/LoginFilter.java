package cn.edu.swu.filter.login;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.swu.filter.HttpFilter;

public class LoginFilter extends HttpFilter {
	private String sessionKey;
	private String redirecUrl;
	private String uncheckedUrls;
	
	protected void init() {
		ServletContext servletContext = getFilterconfig().getServletContext();
		
		sessionKey = servletContext.getInitParameter("userSessionKey");
		redirecUrl = servletContext.getInitParameter("rediretPage");
		uncheckedUrls = servletContext.getInitParameter("uncheckedUrls");
	}
public void  doFilter(HttpServletRequest request,HttpServletResponse response ,FilterChain chain) throws IOException, ServletException{
	//1.获取请求的servletPah
	String servletPath = request.getServletPath();
	//2.检查1获取的servletPath是否为不需要检查的URL中的一个，若是，则直接放行，方法结束
	List<String> urls = Arrays.asList(uncheckedUrls.split(","));
	if(urls.contains(servletPath)) {
		chain.doFilter(request,response);
		return ;
	}
	//3.从session中获取sessionKey对应的值，若值不存在，则重定向到redirectUrl
	Object user = request.getSession().getAttribute(sessionKey);
	if(user == null) {
		response.sendRedirect(request.getContextPath() + redirecUrl);
	}
	//4.若存在，则放行，允许访问
	chain.doFilter(request, response);
	}	



}

