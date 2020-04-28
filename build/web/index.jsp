<%-- 
    Document   : home
    Created on : Apr 28, 2020, 10:11:05 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>

    <body>
        <c:if test="${sessionScope.USER != null}">
            <c:if test="${not empty sessionScope.USER.fullname}">
                <h1>Welcome ${sessionScope.USER.fullname}!</h1>
            </c:if>
        </c:if>

        <c:if test="${sessionScope.USER == null}">
            <a href="login.jsp">Login/Register</a>
        </c:if>

        <c:if test="${sessionScope.USER != null}">
            <c:url var="Logout" value="MainController">
                <c:param name="action" value="Logout"></c:param>
            </c:url>
            <a href="${Logout}">Logout</a>
        </c:if>

    </body>
</html>
