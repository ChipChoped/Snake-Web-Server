<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title> Log In </title>
</head>
<body>
<%@ include file="menu.jsp" %>

<h1> Log in to your account </h1>

<form action="log-in" method="POST" autocomplete="on">
    <fieldset>
        <c:if test="${ error != null}">
            <label> ${ error }</label>
            <br/><br/>
        </c:if>

        <label for="username"> Username </label>
        <input id="username" name="username">
        <br/><br/>

        <label for="password"> Password </label>
        <input type="password" id="password" name="password">
        <br/><br/>

        <input type="checkbox" id="remember-me" name="remember-me" value="remember-me"/>
        <label for="remember-me"> Stay logged in </label>
        <br/><br/>

        <button type="submit"> Log in </button>
    </fieldset>
</form>
</body>
</html>