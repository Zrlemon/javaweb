<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./CSS/Login.css"/>
</head>
<body>
	<div id="login">  
        <h1>Login</h1>  
        <form  action="loginServlet" method="post">  
            <input type="text" required="required" placeholder="user" name="username"></input>  
            <input type="password" required="required" placeholder="password" name="password"></input>  
            <button formaction="./login.jsp" class="but" type="submit">登录</button><br>
	    <button formaction="./registration.jsp"class="but" type="submit">注册</button> 
        </form>  
	</div>
</body>
</html>
