<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table>
    <tr>
        <th><a href="home"> Home </a></th>

        <c:choose>
            <c:when test="${ !empty sessionScope.username or !empty cookie.username}">
                <th><a href="log-out"> Log out </a></th>
                <th><a href="user"> Profile </a></th>
            </c:when>
            <c:otherwise>
                <th><a href="sign-up"> Sign up </a></th>
                <th><a href="log-in"> Log in </a></th>
            </c:otherwise>
        </c:choose>
    </tr>
</table>