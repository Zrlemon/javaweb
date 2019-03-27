package cn.edu.swu;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class loginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String sql = "SELECT count(id) FROM login WHERE username = ? " + "AND password = ?";
		PrintWriter out = resp.getWriter();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql:///login";
			String user = "root";
			String password2 = "zsl980328";
			
			connection = DriverManager.getConnection(url,user,password2);
			
			statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, password);
			
			resultSet = statement.executeQuery();
			
			
			
			if(resultSet.next()) {
				int count = resultSet.getInt(1);
				
				if(count > 0) {
					out.print("Hello: " + username);
				}else {
					out.print("Sorry: " + username);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}



}

