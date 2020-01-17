<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:useBean id="greeting" scope="request" type="java.lang.String"/>
<jsp:useBean id="users" scope="request" type="java.util.List<mate.academy.internet.shop.model.User>"/>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AllUsers</title>
</head>
<body>
Hello, ${greeting}! This is all users page

<table border="1">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Email</th>
        <th>Delete</th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>
                <c:out value="${user.id}" />
            </td>
            <td>
                <c:out value="${user.name}" />
            </td>
            <td>
                <c:out value="${user.surname}" />
            </td>
            <td>
                <c:out value="${user.email}" />
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/servlet/deleteUser?user_id=${user.id}">DELETE</a>
            </td>
        </tr>
    </c:forEach>
</table>
<p><a href="${pageContext.request.contextPath}/index">Main Page</a></p>
<p><a href="${pageContext.request.contextPath}/servlet/bucket">Your bucket</a></p>
<p><a href="${pageContext.request.contextPath}/getAllItems">All items</a></p>

</body>
</html>
