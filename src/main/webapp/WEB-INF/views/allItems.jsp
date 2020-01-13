<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:useBean id="greeting" scope="request" type="java.lang.String"/>
<jsp:useBean id="items" scope="request" type="java.util.List<mate.academy.internet.shop.model.Item>"/>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AllItems</title>
</head>
<body>
Hello, ${greeting}! There are excellent goods in this shop!

<table border="1">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Price</th>
        <th>Add to bucket</th>

    </tr>
    <c:forEach var="item" items="${items}">
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
                <a href="${pageContext.request.contextPath}/servlet/addItemToBucket?item_Id=${item.id}">ADD</a>
            </td>
        </tr>
    </c:forEach>
</table>
<p><a href="${pageContext.request.contextPath}/servlet/registration">Registration</a></p>
<p><a href="${pageContext.request.contextPath}/servlet/bucket">Your bucket</a></p>
<p><a href="${pageContext.request.contextPath}/servlet/index">Main Page</a></p>
</body>
</html>
