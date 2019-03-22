<%@ page contentType ="text/html; charset=utf-8" session="true" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Demo App</title>
	</head>
	<body>
		<center>
		<div style="background-color:#ddd;padding:30;width:30%;margin-top:5em">
			<form action="./login.action" method="post">
				用户： <input type="text" name="user"> <p>
				密码： <input type="password" name="pass">  <p>
				<input type="submit" value=" 登 录 ">
			</form>
		</div>
		</center>
	</body>
</html>
