<%@page import="cn.edu.swu.mvc.domain.Customer"%>
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
		Object msg = request.getAttribute("massage");
		if(msg != null){
	%>
	<br>
	<font color="red"><%= msg %></font>
	<% 
		}
		Customer customer = (Customer)request.getAttribute("customer");
%>
<form action="update.do" method="post">
				<input type="hidden" name="id" value="<%= customer.getId()%>"/>
				<input type="hidden" name="oldName" value="<%= customer.getName()%>"/>
                <table>
                        <tr>
                                <td>CustomerName:</td>
                                <td><input type="text" name="name" 
                                value="<%= customer.getName()%>"/></td>
                        </tr>
                        <tr>
                                <td>Address:</td>
                                <td><input type="text" name="address"
                                value="<%= customer.getAddress()%>"/></td>
                        </tr>
                        <tr>
                                <td>Phone:</td>
                                <td><input type="text" name="phone"
                                value="<%= customer.getPhone()%>"/></td>
                        </tr>
                        <tr>
                                <td><input type="submit" value="更新"/></td>
                        </tr>
                </table>
        </form>
</body>
</html>
