<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign In</title>
</head>
<body>
<%@ include file="menu.jsp" %>

<h1> New account form </h1>

<form action="sign-in" method="POST" autocomplete="on">
	<fieldset>
		<label for="name"> Username </label>
		<input type="text" id="username" name="username"/>
		<br/> <br/>
		
		<label for="age"> Age </label>
		<input type="number" id="age" name="age"/>
		<br/> <br/>
		
		<label for="email"> E-mail </label>
		<input type="email" id="email" name="email"/>
		<br/> <br/>
		
		<label for="email-rep"> Repeat e-mail </label>
		<input type="email" id="email-rep" name="email-rep"/>
		<br/> <br/>
		
		<label for="password"> Password </label>
		<input type="password" id="password" name="password"/>
		<br/> <br/>
		
		<label for="password-rep"> Repeat password </label>
		<input type="password" id="password-rep" name="password-rep"/>
		<br/> <br/>
		
		<button type="submit"> Create account </button>
	</fieldset>
</form>
</body>
</html>