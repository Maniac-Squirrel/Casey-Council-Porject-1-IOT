<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.*" %>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.JsonObject"%>
<%@ page import="webapp.DateFloatHolder" %>
<%@ page import="webapp.EncryptionUtil" %>
<%@ page import="webapp.UserPrefs" %>
 

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/userProfilea.css" media="screen"/>
	<link href="http://fonts.googleapis.com/css?family=Lato:100italic,100,300italic,300,400italic,400,700italic,700,900italic,900" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="css/upsty.css" />
<ul>
  <li><form action="index.jsp" method="post">
		<input type="submit" value="Log out"/>
		</form></li>	
		
  <li><form action="profile?id=<%= request.getParameter("id") %>" method="post">
		<a class = "active"><input type="submit" value="User Preferences"/></a>
		</form></li>
		
  <li><form action="community?id=<%= request.getParameter("id") %>" method="post">
		<input type="submit" value="Community Graphs"/>
		</form></li>
		
  <li><form action="home?id=<%= request.getParameter("id") %>" method="post">
		<input type="submit" value="Individual Graphs"/>
		</form></li>
		
<%
Gson gsonObj = new Gson();
String Username = (String)UserPrefs.getCuName(EncryptionUtil.decode(request.getParameter("id"))); 
String user = gsonObj.toJson(Username);

%>
		
  <li style="float:left"><a class = userLabel id= "label"><script>document.write(<%out.print(user);%>)</script></a></li>
  
  <li style="float:left"><a class = userLabel><script>var options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' }; document.write(new Date().toLocaleDateString("en-US", options));</script></a></li>

</ul>
<meta charset="ISO-8859-1">
<title>Profile</title>
</head>
<body>

<section class="container">
			<section class="login-form">
				<form method="post" action="profile?id=<%= request.getParameter("id") %>" role="login">
					<h2>Update details</h2>
					<p>Please enter new username and password, if you would like to continue using the same username or password please re-enter it in the field below!</p>
					<div class="form-group">
	    				<div class="input-group">
		      				<div class="input-group-addon"><span class="text-primary glyphicon glyphicon-user"></span></div>
							<input type="text" name="Username" placeholder="Username" required class="form-control" />
						</div>
					</div>
					<div class="form-group">
	    				<div class="input-group">
		      				<div class="input-group-addon"><span class="text-primary glyphicon glyphicon-lock"></span></div>
							<input type="password" name="Password" placeholder="Password" required class="form-control" />
						</div>
					</div>
					<input type="hidden" name="Update" value="true"/>
					<button type="submit" name="go" class="btn btn-block btn-success">Update</button>
					<br>
				</form>
			</section>
	</section>
	
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="css/bootstrap.min.js"></script>

</html>