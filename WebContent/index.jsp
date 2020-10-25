<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=1,initial-scale=1,user-scalable=1" />
	<title>Login</title>
	
	<link href="http://fonts.googleapis.com/css?family=Lato:100italic,100,300italic,300,400italic,400,700italic,700,900italic,900" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="css/stylemain.css" />
	
</head>
<body>

	<section class="container">
			<section class="login-form">
				<form method="post" action="login" role="login">
					<h2>Please sign in</h2>
					<p>To access your dashboards</p>
					<div class="form-group">
	    				<div class="input-group">
		      				<div class="input-group-addon"><span class="text-primary glyphicon glyphicon-user"></span></div>
							<input type="text" name="username" placeholder="Username" required class="form-control" />
						</div>
					</div>
					<div class="form-group">
	    				<div class="input-group">
		      				<div class="input-group-addon"><span class="text-primary glyphicon glyphicon-lock"></span></div>
							<input type="password" name="password" placeholder="Password" required class="form-control" />
						</div>
					</div>
					<button type="submit" name="go" class="btn btn-block btn-success">Sign in</button>
					<br>
				</form>
			</section>
	</section>
	
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="css/bootstrap.min.js"></script>
</body>
</html>
