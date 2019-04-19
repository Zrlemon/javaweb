package cn.edu.swu.mvc.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {
	
	public static void releaseConnection(Connection connection) {
		try {
			if(connection != null) {
				connection.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static DataSource dataSource = null;
	
	static {
		//数据源只能被创建一次
		dataSource = new ComboPooledDataSource("mvcapp");
		
	}
	
	public static Connection getConnection() throws SQLException {
		
		return dataSource.getConnection();
		
	}

}

