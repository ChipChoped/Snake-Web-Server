<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Snake</title>
</head>
<body>
<%@ include file="menu.jsp" %>

<h1> Accueil Snake </h1>
<p> 
	<% 
		String variable = (String) request.getAttribute("variable");
		out.print(variable);
	%> 
</p>
</body>
</html>