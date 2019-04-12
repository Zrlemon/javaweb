<%@page import="cn.edu.swu.Student"%>
<jsp:directive.page import="java.util.List" />
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<%
	List<Student> names = (List<Student>)request.getAttribute("students");%>

	
		<table border="1" cellpadding="10" cellspacing="0">
		
			<tr>
				<th>Id</th>
				<th>type</th>
				<th>name</th>
				<th>grade</th>
				<th>Delete</th>
			</tr>
			<%
			
			for(Student student:names){
			%>
				<tr> 
				
					<td><%= student.getId() %></td>
					<td><%= student.getType()%></td>
					<td><%= student.getName()%></td>
					<td><%= student.getGrade()%></td>
					<td><a href="deleteStudent?Id=<%=student.getId()%>">Delete</a></td>
				
				</tr>
			<% 	
			}
			%>
			
			
		</table>




</body>
</html>
