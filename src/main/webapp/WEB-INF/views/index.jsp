<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index</title>
</head>
<body>
Welcome to our shop!
<p><a href="${pageContext.request.contextPath}/servlet/addItem">Add new item</a></p>
<p><a href="${pageContext.request.contextPath}/servlet/getAllItems">All items</a></p>
<p><a href="${pageContext.request.contextPath}/servlet/registration">Registration</a></p>
<p><a href="${pageContext.request.contextPath}/servlet/bucket">Your bucket</a></p>
<p><a href="${pageContext.request.contextPath}/servlet/orders">Your orders</a></p>
</body>
</html>
