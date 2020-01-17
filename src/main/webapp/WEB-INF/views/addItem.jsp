<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add item</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/servlet/addItem" method="post">
    <div class="container">
        <h1>Add item to storage</h1>
        <p>Please fill in this form to add an item.</p>
        <hr>

        <label for="item_name"><b>Name</b></label>
        <input type="text" placeholder="Please, enter item name" name="item_name" required>

        <label for="item_price"><b>Price</b></label>
        <input type="text" placeholder="Please, set the price" name="item_price" required>

        <button type="submit" class="registerbtn">Add</button>
    </div>
</form>
<p><a href="${pageContext.request.contextPath}/index">Main Page</a></p>
</body>
</html>
