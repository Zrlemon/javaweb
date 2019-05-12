<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>	
			<br><br>
			<form action="authorityServlet?method=getAuthorities" method = "post">
				name:<input type ="text" name="username"/>
				<input type="submit" value="sumit"/>		
			</form>
	<c:if test="${ requestScope.user != null}">
	<br><br>
		${requestScope.user.username} 的权限是： 
	<br><br>
	<form action="authorityServlet?method=updateAuthorities" method="post">
		<input type ="hidden" name="username" value="${requestScope.user.username}"/>
		<c:forEach items="${authorities }" var="auth">
		<c:set var="flag" value="false"></c:set>
			<c:forEach items="${user.authorities}" var="ua">
				<c:if test="${ua.url == auth.url }">
					<c:set var="flag" value="true"></c:set>
				</c:if>
			</c:forEach>
				<c:if test="${flag == true }">
					<input type="checkbox" name="authority" checked="checked" value ="${auth.url }"/>${auth.displayName }
				</c:if>
				<c:if test="${flag == false }">
					<input type="checkbox" name="authority"  value ="${auth.url }"/>${auth.displayName }
				</c:if>
		<br><br>
		</c:forEach>
		<input type="submit" value="Update"/>
	</form>
	</c:if>
</body>
</html>