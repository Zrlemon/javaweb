<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import = "java.io.*,java.sql.*,javax.servlet.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>registration</title>
</head>
<body>

<%
String username = request.getParameter("username");
String password = request.getParameter("password");
String sql = "INSERT INTO user VALUES(null,?,?)";
Connection connection = null;
PreparedStatement statement = null;
try {
	Class.forName("com.mysql.jdbc.Driver");
	String url = "jdbc:mysql:///login";
	String user = "root";
	String password2 = "zsl980328";
	
	connection = DriverManager.getConnection(url,user,password2);
	
	statement = connection.prepareStatement(sql);
	statement.setString(1, username);
	statement.setString(2, password);
	
	statement.executeUpdate();
	out.println("恭喜你成功注册！！");	
} catch (Exception e) {
	e.printStackTrace();
} finally {

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




%>
</body>
</html>
