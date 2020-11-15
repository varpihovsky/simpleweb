<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/controller">
        <label for="userNameInput">Username:</label>
        <input type="text" name="username" value="" id="userNameInput">
        <label for="emailInput">Email:</label>
        <input type="email" name="email" value="" id="emailInput">
        <label for="passwordInput">Password:</label>
        <input type="password" name="password" value="" id="passwordInput">
        <input type="hidden" name="page" value="/register.jsp">
        <input type="hidden" name="send" value="signUp">
        <button type="submit">Submit</button>
    </form>

${message}

<a href="${pageContext.request.contextPath}/controller?page=/main.jsp&send=redirect">Go back</a>
</body>
</html>
