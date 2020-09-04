<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
		<h1>Please Login to View Dashboard</h1>
		<form action="dashboard" method="post">
		Username: <input type="text" name="username" width="30"/>
		Password: <input type="password" name="password" width="10"/>
		<input type="submit" value="Login"/>
		</form>
</body>
</html>