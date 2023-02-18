<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="fr.snake.beans.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome!</title>
</head>
<body>
<%@ include file="menu.jsp" %>

<h1> Welcome <c:out value="${ user.username }"/>! </h1>
<h2> Your account has been successfully created :D </h2>
</body>
</html>