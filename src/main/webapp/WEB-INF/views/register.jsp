<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>

<div>${errorEmailAlready}</div>

<form action="${pageContext.request.contextPath}/registration" method="post">
    <div class="container">
        <h1>Register</h1>
        <p>Please fill in this form to create an account.</p>
        <hr>

        <label for="user_name"><b>Name</b></label>
        <input type="text" placeholder="Please, enter your name" name="user_name" required>

        <label for="user_surname"><b>Surname</b></label>
        <input type="text" placeholder="Please, enter your surname" name="user_surname" required>

        <label for="email"><b>Email</b></label>
        <input type="text" placeholder="Enter Email" name="email" required>

        <label for="psw"><b>Password</b></label>
        <input type="password" placeholder="And Password" name="psw" required>

        <label for="psw-repeat"><b>Repeat Password</b></label>
        <input type="password" placeholder="Please, repeat Password" name="psw-repeat" required>
        <hr>

        <button type="submit" class="registerbtn">Register</button>
    </div>
    <p><a href="${pageContext.request.contextPath}/login">Sign in</a></p>
    </div>
</form>
<p><a href="${pageContext.request.contextPath}/index">Main Page</a></p>
</body>
</html>
