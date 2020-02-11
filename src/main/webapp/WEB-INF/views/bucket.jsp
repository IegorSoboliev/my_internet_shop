<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:useBean id="greeting" scope="request" type="java.lang.String"/>
<jsp:useBean id="bucket" scope="request" type="mate.academy.internet.shop.model.Bucket"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bucket</title>
</head>
<body>
This is your bucket

<table border="1">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Price</th>
        <th>Remove</th>

    </tr>
    <c:forEach var="item" items="${bucket.items}">
        <tr>
            <td>
                <c:out value="${item.id}" />
            </td>
            <td>
                <c:out value="${item.name}" />
            </td>
            <td>
                <c:out value="${item.price}" />
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/servlet/removeItemFromBucket?item_id=${item.id}">REMOVE</a>
            </td>
        </tr>
    </c:forEach>
</table>
<p><a href="${pageContext.request.contextPath}/servlet/completeOrder">COMPLETE ORDER</a></p>
<p><a href="${pageContext.request.contextPath}/getAllItems">All items</a></p>
<p><a href="${pageContext.request.contextPath}/index">Main Page</a></p>
</body>
</html>
