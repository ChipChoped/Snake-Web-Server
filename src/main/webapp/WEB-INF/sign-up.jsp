<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title> Sign In </title>
</head>
<body>
<%@ include file="menu.jsp" %>

<h1> New account form </h1>

<form action="sign-up" method="POST" autocomplete="on" enctype="multipart/form-data">
	<fieldset>
		<label for="username"> Username </label>
		<input type="text" id="username" name="username"/>
		<c:if test="${ !form.validUsername && !firstAttempt }">
			<label for="username"> Please enter a valid username (3-20 characters : letters, numbers and underscore) </label>
		</c:if>
		<c:if test="${ form.usernameTaken && !firstAttempt }">
			<br/> <br/>
			<label for="username"> This username is already taken </label>
		</c:if>
		<br/> <br/>

		<label for="first-name"> First name </label>
		<input type="text" id="first-name" name="first-name"/>
		<c:if test="${ !form.validFirstName && !firstAttempt }">
			<label for="first-name"> Please enter a valid name (Only letters) </label>
		</c:if>
		<br/> <br/>

		<label for="last-name"> Last name </label>
		<input type="text" id="last-name" name="last-name"/>
		<c:if test="${ !form.validLastName && !firstAttempt }">
			<label for="last-name"> Please enter a valid name (Only letters) </label>
		</c:if>
		<br/> <br/>

		<label> Sex </label>
		<input type="radio" checked="checked" id="male" name="sex" value="M">
		<label for="male"> Male </label>
		<input type="radio" id="female" name="sex" value="F">
		<label for="female"> Female </label>
		<c:if test="${ !form.checkedSex && !firstAttempt }">
			<label for="last-name"> One option must be checked </label>
		</c:if>
		<br/> <br/>

		<label for="birth-date"> Birth date </label>
		<input type="date" id="birth-date" name="birth-date">
		<c:if test="${ !form.validAge && !firstAttempt }">
			<label for="birth-date"> You must be over 13 </label>
		</c:if>
		<br/> <br/>
		
		<label for="email"> E-mail </label>
		<input type="email" id="email" name="email"/>
		<c:if test="${ !form.validEmail && !firstAttempt }">
			<label for="email"> Please enter a valid e-mail address </label>
		</c:if>
		<c:if test="${ form.emailTaken && !firstAttempt }">
			<br/> <br/>
			<label for="email"> This email is already taken </label>
		</c:if>
		<br/> <br/>
		
		<label for="email-rep"> Repeat e-mail </label>
		<input type="email" id="email-rep" name="email-rep"/>
		<c:if test="${ !form.sameEmails && !firstAttempt }">
			<label for="email-rep"> Please enter the same e-mail address </label>
		</c:if>
		<br/> <br/>
		
		<label for="password"> Password </label>
		<input type="password" id="password" name="password"/>
		<c:if test="${ !form.validPassword && !firstAttempt }">
			<label for="password"> Please enter a password with over 8 characters</label>
		</c:if>
		<br/> <br/>
		
		<label for="password-rep"> Repeat password </label>
		<input type="password" id="password-rep" name="password-rep"/>
		<c:if test="${ !form.samePasswords && !firstAttempt }">
			<label for="password-rep"> Please enter the same password </label>
		</c:if>
		<br/> <br/>

		<label for="profil-picture"> Profile picture </label>
		<input type="file" id="profil-picture" name="profile-picture">
		<br/> <br/>
		
		<button type="submit"> Create account </button>
	</fieldset>
</form>
</body>
</html>