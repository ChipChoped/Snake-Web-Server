<jsp:useBean id="user" scope="request" type="fr.snake.beans.User"/>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title> Log In </title>
</head>
<link rel="stylesheet" type="text/css" href="../ressources/css/profil.css">
<body>
<%@ include file="menu.jsp" %>

<div class="profil-card">
    <!--
    <span> ${ pageContext.request.contextPath } </span>
    <img class="profil-picture" src="${ pageContext.request.contextPath }/image/ChipChop.jpg" alt="Profil picture"/>
    -->
    <div>
        <h1> ${ user.username } </h1>
        <h2> ${ user.firstName } ${ user.lastName }</h2>
        <h3> Member since ${ user.inscriptionDate } </h3>
    </div>
</div>
</body>
</html>