<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title> Log In </title>
    <link rel="stylesheet" type="text/css" href="../ressources/css/profile.css">
</head>
<body>
<%@ include file="menu.jsp" %>

<div class="profile-card">
    <img class="profile-picture" src="../ressources/images/profile_pictures/ChipChop.jpg" alt="Profile picture"/>

    <div>
        <h1> ${ user.username } </h1>
        <h2> ${ user.firstName } ${ user.lastName }</h2>
        <h3> Won ${ user.victories } game(s) </h3>
        <h3> Member since ${ user.inscriptionDate } </h3>
    </div>

    <c:if test="${ !empty sessionScope.username or !empty cookie.username}">
        <button> <a href="settings/profile"></a> </button>
    </c:if>
</div>
</body>
</html>