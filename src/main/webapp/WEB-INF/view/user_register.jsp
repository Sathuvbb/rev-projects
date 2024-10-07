<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>

<html>
<head>
    <title>User Registration</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 40%;
            margin: 50px auto;
            padding: 20px;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
        }
        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
        }
        input[type="text"], input[type="email"], input[type="password"], select {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        .error-message {
            color: red;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>User Registration</h2>
        <form action="${pageContext.request.contextPath}/user/register" method="post">
            <label for="firstName">First Name:</label>
            <input type="text" name="firstName" placeholder="First Name" required /><br/>

            <label for="lastName">Last Name:</label>
            <input type="text" name="lastName" placeholder="Last Name" required /><br/>

            <label for="email">Email:</label>
            <input type="email" name="email" placeholder="Email" required /><br/>

            <label for="password">Password:</label>
            <input type="password" name="password" placeholder="Password" required /><br/>

            <label for="role">Role:</label>
            <select name="role" required>
                <option value="USER">User</option>
                <option value="ADMIN">Admin</option>
            </select><br/>

            <input type="submit" value="Register" />
        </form>

       <c:if test="${not empty error}"> 
            <p class="success-message">${error}</p>
        </c:if> 

        <p>Already registered? <a href="${pageContext.request.contextPath}/user/login">Login here</a></p>
    </div>
</body>
</html>
