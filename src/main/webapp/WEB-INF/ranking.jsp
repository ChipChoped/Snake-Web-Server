<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title> Ranking </title>
</head>
<body>
<%@ include file="menu.jsp" %>

<table>
    <tr>
        <th> # </th>
        <th> Username </th>
        <th> Number of victories </th>
    </tr>

    <jsp:useBean id="users" scope="request" type="java.util.List"/>
    <c:forEach items="${ users }" var="user" varStatus="status">
        <tr>
            <th> <c:out value="${ status.count }"/> </th>
            <th> <c:out value="${ user.username }"/> </th>
            <th> <c:out value="${ user.victories }"/> </th>
        </tr>
    </c:forEach>
</table>
</body>
</html>