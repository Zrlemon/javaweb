package cn.edu.swu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class StudentDao {
	
	public void DeleteById(int Id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			String url = "jdbc:mysql:///test";
			String user = "root"; 
			String Password = "zsl980328";
			
			connection = DriverManager.getConnection(url,user,Password);
			
			
			String sql = "DELETE FROM student WHERE Id=?";
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, Id);
			
			preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
		
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				} 
			} catch (Exception e2) {
				e.printStackTrace();
			}
			try {
				if (connection != null) {
					connection.close();
				} 
			} catch (Exception e2) {
				e.printStackTrace();
			}
		}

					
	}
		

	
	private Throwable e;
	List<Student> student = new ArrayList<>();

	public List<Student> getAll() {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			String url = "jdbc:mysql:///test";
			String user = "root"; 
			String Password = "zsl980328";
			
			connection = DriverManager.getConnection(url,user,Password);
			
			
			String sql = "SELECT Id,type,name,grade FROM student";
			
			preparedStatement = connection.prepareStatement(sql);
			
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				int Id = resultSet.getInt(1);
				int type = resultSet.getInt(2);
				String name = resultSet.getString(3);
				int grade = resultSet.getInt(4);
			
				
				Student students = new Student(Id, type, name, grade);
				
			student.add(students);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				} 
			} catch (Exception e2) {
				e.printStackTrace();
			}
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				} 
			} catch (Exception e2) {
				e.printStackTrace();
			}
			try {
				if (resultSet != null) {
					resultSet.close();
				} 
			} catch (Exception e2) {
				e.printStackTrace();
			}
		}
		
		return student;
					
	}

}

