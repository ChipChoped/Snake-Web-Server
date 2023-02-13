<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table>
    <tr>
        <th><a href="/Snake/home"> Home </a></th>

        <c:choose>
            <c:when test="${ !empty sessionScope.userID or !empty cookie.userID}">
                <th><a href="/Snake/log-out"> Log out </a></th>
                <th><a href="/Snake/user"> Profile </a></th>
            </c:when>
            <c:otherwise>
                <th><a href="/Snake/sign-up"> Sign up </a></th>
                <th><a href="/Snake/log-in"> Log in </a></th>
            </c:otherwise>
        </c:choose>
    </tr>
</table>