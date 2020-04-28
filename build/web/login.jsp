<%-- 
    Document   : login
    Created on : Apr 28, 2020, 10:24:02 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>

        <font color="red"> ${LOGINERROR} </font>

        <form action="MainController" method="POST">
            <input type="email" name="txtEmail" required="true"/> <br/>
            <input type="password" name="txtPassword" required="true"/> <br/>
            <input type="submit" name="action" value="Login"/> <br/>
        </form>
        <a href="register.jsp">Register here</a>

    </body>
</html>
