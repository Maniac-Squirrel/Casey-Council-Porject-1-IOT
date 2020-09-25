<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profile</title>
</head>
<body>
		<h1>Update Account Details</h1>

		<p>Please enter new username and password, if you would like to continue using the same username or password please re-enter it in the field below!</p>

		<form action="profile?id=<%= request.getParameter("id") %>" method="post">
		Username: <input type="text" name="Username" width="30"/>
		Password: <input type="password" name="Password" width="10"/>
				<input type="hidden" name="Update" value="true"/>
		<input type="submit" value="Update"/>
		</form>
		
		<br>
		
		<form action="community?id=<%= request.getParameter("id") %>" method="post">
		<input type="submit" value="Community Graphs"/>
		</form>
		
		<br>
		
<form action="home?id=<%= request.getParameter("id") %>" method="post">
		<input type="submit" value="Individual Graphs"/>
		</form>		
		
</body>
<br>
</html>