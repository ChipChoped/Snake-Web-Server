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
    <c:choose>
        <c:when test="${ !empty profilePicture }">
            <img src="data:image/jpg;base64,${ requestScope['profilePicture'] }" alt="Profile picture"/>
        </c:when>
        <c:otherwise>
            <img src="../ressources/images/default_profile_picture.png" alt="Profile picture"/>
        </c:otherwise>
    </c:choose>
    <div>
        <h1> ${ user.username } </h1>
        <h2> ${ user.firstName } ${ user.lastName }</h2>
        <h3> Won ${ user.victories } game(s) </h3>
        <h3> Member since ${ user.inscriptionDate } </h3>
    </div>

    <c:set var="id" value="${ Integer.toString(user.id) }"/>
    <c:if test="${ sessionScope.userID == id || cookie.userID.value == id }">
        <button> <a href="../settings/profile"> Modify </a> </button>
    </c:if>
</div>
</body>
</html>