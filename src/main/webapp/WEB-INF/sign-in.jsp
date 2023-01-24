<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<c:if test="${ !form.validUsername && !firstAttempt }">
			<label for="name"> Please enter a valid username (3-20 characters : letters, numbers and underscore) </label>
		</c:if>
		<br/> <br/>
		
		<label for="age"> Age </label>
		<input type="number" id="age" name="age" min="1"/>
		<c:if test="${ !form.ageOver13 && !firstAttempt }">
			<label for="age"> You must be over 13 to create an account </label>
		</c:if>
		<br/> <br/>
		
		<label for="email"> E-mail </label>
		<input type="email" id="email" name="email"/>
		<c:if test="${ !form.validEmail && !firstAttempt }">
			<label for="age"> Please enter a valid e-mail address </label>
		</c:if>
		<br/> <br/>
		
		<label for="email-rep"> Repeat e-mail </label>
		<input type="email" id="email-rep" name="email-rep"/>
		<c:if test="${ !form.sameEmails && !firstAttempt }">
			<label for="age"> Please enter the same e-mail address </label>
		</c:if>
		<br/> <br/>
		
		<label for="password"> Password </label>
		<input type="password" id="password" name="password"/>
		
		<c:if test="${ !form.passwordOver8Characters && !firstAttempt }">
			<label for="age"> Please enter a password with over 8 characters</label>
		</c:if>
		<br/> <br/>
		
		<label for="password-rep"> Repeat password </label>
		<input type="password" id="password-rep" name="password-rep"/>
		<c:if test="${ !form.samePasswords && !firstAttempt }">
			<label for="age"> Please enter the same password </label>
		</c:if>
		<br/> <br/>
		
		<button type="submit"> Create account </button>
	</fieldset>
</form>
</body>
</html>