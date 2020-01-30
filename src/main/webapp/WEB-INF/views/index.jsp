<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index</title>
</head>
<body>
Welcome to our shop!
<p><a href="${pageContext.request.contextPath}/getAllItems">All items</a></p>
<p><a href="${pageContext.request.contextPath}/registration">Registration</a></p>
<p><a href="${pageContext.request.contextPath}/login">Sign in</a></p>
<p><a href="${pageContext.request.contextPath}/servlet/logout">Logout</a><p>
<p><a href="${pageContext.request.contextPath}/servlet/bucket">Your bucket</a></p>
<p><a href="${pageContext.request.contextPath}/servlet/getUserOrders">Your orders</a></p>
<p><a href="${pageContext.request.contextPath}/servlet/getAllUsers">All registered users</a></p>
</body>
</html>
