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

        <a href="IndexController">Home</a>
        &ensp;
        <c:if test="${sessionScope.USER == null}">
            <a href="login.jsp">Login/Register</a>
        </c:if>

        <c:if test="${sessionScope.USER != null}">
            <c:url var="Logout" value="MainController">
                <c:param name="action" value="Logout"></c:param>
            </c:url>
            <a href="${Logout}">Logout</a>
        </c:if>

        <form action="MainController" method="POST">
            Hotel: <input type="text" name="txtHotel"/>
            Area: <select name="txtArea">
                <option value="All">All Area</option>
                <c:forEach var="listArea" items="${requestScope.LIST_AREA}">
                    <option value="${listArea.location}">${listArea.location}</option>
                </c:forEach>
            </select>
            Check In: <input type="date" name="txtCheckIn" required="true"/>
            Check Out: <input type="date" name="txtCheckOut" required="true"/>
            <input type="submit" name="action" value="Search"/>
        </form>

    </body>
</html>
