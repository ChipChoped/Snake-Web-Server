<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.snake.beans.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome!</title>
</head>
<body>
<h1> Welcome
<% User user = (User) request.getAttribute("user");%> ${ user.getUsername() }! </h1>
<h2> Your account has been successfully created :D </h2>
</body>
</html>