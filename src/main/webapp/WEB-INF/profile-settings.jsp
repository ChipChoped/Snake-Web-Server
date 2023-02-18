<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="fr.snake.enums.InfoState" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title> Log In </title>
</head>
<link rel="stylesheet" type="text/css" href="../ressources/css/profile.css">
<body>
<%@ include file="menu.jsp" %>

<h2> Update my information </h2>

<form action="profile" method="POST" autocomplete="on">
    <fieldset>
        <label for="username"> Username </label>
        <input type="text" id="username" name="username" value="${ user.username }"/>

        <button type="submit" name="submit" value="username"> Update </button>

        <c:choose>
            <c:when test="${ form.usernameState == InfoState.INVALID && passedSubmit == 'username' }">
                <label for="username"> Please enter a valid username (3-20 characters : letters, numbers and underscore) </label>
            </c:when>
            <c:when test="${ form.usernameState == InfoState.TAKEN && passedSubmit == 'username' }">
                <label for="username"> This username is already taken </label>
            </c:when>
            <c:when test="${ form.usernameState == InfoState.UNCHANGED && passedSubmit == 'username' }">
                <label for="username"> Please change your username before updating it </label>
            </c:when>
            <c:when test="${ form.usernameState == InfoState.UPDATABLE && passedSubmit == 'username' }">
                <label for="username"> Your username has been successfully updated! </label>
            </c:when>
        </c:choose>
        <br/> <br/>

        <label for="first-name"> First name </label>
        <input type="text" id="first-name" name="first-name" value="${ user.firstName }"/>

        <button type="submit" name="submit" value="first-name"> Update </button>

        <c:choose>
            <c:when test="${ form.firstNameState == InfoState.INVALID && passedSubmit == 'first-name' }">
                <label for="first-name"> Please enter a valid name (Only letters) </label>
            </c:when>
            <c:when test="${ form.firstNameState == InfoState.UNCHANGED && passedSubmit == 'first-name' }">
                <label for="first-name"> Please change your first name before updating it </label>
            </c:when>
            <c:when test="${ form.firstNameState == InfoState.UPDATABLE && passedSubmit == 'first-name' }">
                <label for="first-name"> Your first name has been successfully updated! </label>
            </c:when>
        </c:choose>
        <br/> <br/>

        <label for="last-name"> Last name </label>
        <input type="text" id="last-name" name="last-name" value="${ user.lastName }"/>

        <button type="submit" name="submit" value="last-name"> Update </button>

        <c:choose>
            <c:when test="${ form.lastNameState == InfoState.INVALID && passedSubmit == 'last-name' }">
                <label for="last-name"> Please enter a valid name (Only letters) </label>
            </c:when>
            <c:when test="${ form.lastNameState == InfoState.UNCHANGED && passedSubmit == 'last-name' }">
                <label for="last-name"> Please change your last name before updating it </label>
            </c:when>
            <c:when test="${ form.lastNameState == InfoState.UPDATABLE && passedSubmit == 'last-name' }">
                <label for="last-name"> Your last name has been successfully updated! </label>
            </c:when>
        </c:choose>
        <br/> <br/>

        <label> Sex </label>

        <c:choose>
            <c:when test="${ user.sex == 'M' }">
                <input type="radio" id="male" name="sex" value="M" checked="checked">
                <label for="male"> Male </label>

                <input type="radio" id="female" name="sex" value="F">
                <label for="female"> Female </label>
            </c:when>
            <c:when test="${ user.sex == 'F' }">
                <input type="radio" id="male" name="sex" value="M">
                <label for="male"> Male </label>

                <input type="radio" id="female" name="sex" value="F" checked="checked">
                <label for="female"> Female </label>
            </c:when>
        </c:choose>

        <button type="submit" name="submit" value="sex"> Update </button>

        <c:choose>
            <c:when test="${ form.sexState == InfoState.INVALID && passedSubmit == 'sex' }">
                <label> Please enter a valid sex </label>
            </c:when>
            <c:when test="${ form.sexState == InfoState.UNCHANGED && passedSubmit == 'sex' }">
                <label> Please change your sex before updating it </label>
            </c:when>
            <c:when test="${ form.sexState == InfoState.UPDATABLE && passedSubmit == 'sex' }">
                <label> Your sex has been successfully updated! </label>
            </c:when>
        </c:choose>
        <br/> <br/>

        <label for="birth-date"> Birth date </label>
        <input type="date" id="birth-date" name="birth-date" value="${ user.birthDate }">

        <button type="submit" name="submit" value="birth-date"> Update </button>

        <c:choose>
            <c:when test="${ form.birthDateState == InfoState.INVALID && passedSubmit == 'birth-date' }">
                <label> You must be over 13 </label>
            </c:when>
            <c:when test="${ form.birthDateState == InfoState.UNCHANGED && passedSubmit == 'birth-date' }">
                <label> Please change your birthdate before updating it </label>
            </c:when>
            <c:when test="${ form.birthDateState == InfoState.UPDATABLE && passedSubmit == 'birth-date' }">
                <label> Your birthdate has been successfully updated! </label>
            </c:when>
        </c:choose>
    </fieldset>

    <h2> Update email </h2>

    <fieldset>
        <label for="email"> E-mail </label>
        <input type="email" id="email" name="email" value="${ user.email }"/>

        <c:choose>
            <c:when test="${ form.emailState == InfoState.INVALID && passedSubmit == 'email' }">
                <label> Please enter a valid email </label>
            </c:when>
            <c:when test="${ form.emailState == InfoState.TAKEN && passedSubmit == 'email' }">
                <label> This email is already taken </label>
            </c:when>
            <c:when test="${ form.emailState == InfoState.UNCHANGED && passedSubmit == 'email' }">
                <label> Please change your email before updating it </label>
            </c:when>
        </c:choose>
        <br/> <br/>

        <label for="email-rep"> Repeat e-mail </label>
        <input type="email" id="email-rep" name="email-rep"/>

        <c:if test="${ form.emailState == InfoState.INVALID && passedSubmit == 'email' }">
            <label for="email-rep"> Please enter the same e-mail address </label>
        </c:if>
        <br/> <br/>

        <button type="submit" name="submit" value="email"> Update email </button>

        <c:if test="${ form.emailState == InfoState.UPDATABLE && passedSubmit == 'email' }">
            <label> Your email has been successfully updated! </label>
        </c:if>
    </fieldset>

    <h2> Update password </h2>

    <fieldset>
        <label for="current-password"> Current password </label>
        <input type="password" id="current-password" name="current-password"/>

        <c:choose>
            <c:when test="${ form.currentPasswordState == InfoState.INVALID && passedSubmit == 'password' }">
                <label> Wrong password </label>
            </c:when>
            <c:when test="${ form.currentPasswordState == InfoState.UNCHANGED && passedSubmit == 'password' }">
                <label> Please change your password before updating it </label>
            </c:when>
        </c:choose>
        <br/> <br/>

        <label for="password"> New password </label>
        <input type="password" id="password" name="password"/>

        <c:if test="${ form.passwordState == InfoState.INVALID && passedSubmit == 'password' }">
            <label> Please enter a valid password </label>
        </c:if>
        <br/> <br/>

        <label for="password-rep"> Repeat new password </label>
        <input type="password" id="password-rep" name="password-rep"/>

        <c:if test="${ form.passwordState == InfoState.INVALID && passedSubmit == 'password' }">
            <label for="password-rep"> Please enter the same password </label>
        </c:if>
        <br/> <br/>

        <button type="submit" name="submit" value="password"> Update password </button>

        <c:if test="${ form.passwordState == InfoState.UPDATABLE && passedSubmit == 'password' }">
            <label> Your password has been successfully updated! </label>
        </c:if>
    </fieldset>
</form>
</body>
</html>