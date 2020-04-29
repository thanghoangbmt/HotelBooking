<%-- 
    Document   : register
    Created on : Apr 28, 2020, 10:27:04 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
    </head>
    <body>
        <form action="MainController" method="POST" name="myForm"> 
            Email: <input type="email" name="txtEmail" required="true"/> 
            <font color="red">${requestScope.REGISTERERROR.emailError}</font>
            <br/>
            Password: <input type="password" name="txtPassword" id="txtPassword" required="true"/> <br/>
            Confirm Password: <input type="password" name="txtConfirmPassword" id="txtConfirmPassword" required="true"/> <br/>
            Full Name: <input type="text" name="txtFullname" required="true"/> <br/>
            Phone: <input type="text" name="txtPhone" id="phone" required="true"/> <br/>
            Address: <input type="text" name="txtAddress" required="true"/> <br/>

            <input type="submit" name="action" value="Register" onclick="return Validate()"/>
        </form>

        <script src="script01.js"></script>

    </body>
</html>
