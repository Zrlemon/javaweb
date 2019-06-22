package cn.edu.swu.fileupload.listener;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class Myhttpsessionlistener
 *
 */

public class Myhttpsessionlistener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		HttpSession session=se.getSession();
		ServletContext c=session.getServletContext();
		int count=(int) c.getAttribute("count1");
		count++;
		c.setAttribute("count1", count);
		//System.out.println("增加1");
	}
 
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		HttpSession session=se.getSession();
		ServletContext c=session.getServletContext();
		int count=(int) c.getAttribute("count1");
		count--;
		if (count<1) {
			c.setAttribute("count1", 1);
		}
		else {
			c.setAttribute("count1", count);
		}
		
		//System.out.println("减少1");
	}
	
}
