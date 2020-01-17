<%--
  Created by IntelliJ IDEA.
  User: marichka
  Date: 14.01.20
  Time: 12:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<div>${errorAuthentication}</div>

<form action="${pageContext.request.contextPath}/login" method="post">
    <div class="container">
        <p>Please, enter your email and password.</p>
        <hr>
        <label for="email"><b>Email</b></label>
        <input type="text" placeholder="Enter Email" name="email" required>

        <label for="psw"><b>Password</b></label>
        <input type="password" placeholder="And Password" name="psw" required>
        <hr>

        <button type="submit" class="registerbtn">Login</button>
    </div>

    <div class="container signin">
        <p>Don`t have an account? <a href="${pageContext.request.contextPath}/registration">Registration</a></p>

    </div>
</form>
<p><a href="${pageContext.request.contextPath}/index">Main Page</a></p>
</body>
</html>
