<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="css/register.css">
</head>
<body>
<div class="form">
    <form action="${pageContext.request.contextPath}/controller">
        <h1>Регистрация</h1>
        <div class="input-form">
            <p style="color:#FFFFFF"><big>Придумайте логин</big> <br>
                <input type="text" placeholder="Логин" name="username" value="">
        </div>
        <div class="input-form">
            <p style="color:#FFFFFF"><big>Придумайте пароль</big> <br>
                <input type="password" placeholder="Пароль">
        </div>

        <div class="input-form">
            <p style="color:#FFFFFF"><big>Введите почту</big> <br>
                <input type="text" placeholder="Почта" name="username" value="">
        </div>
        <div class="input-form">
            <input type="submit" value="Регистрация">
        </div>

        <input type="hidden" name="send" value="login">
        <input type="hidden" name="page" value="login.jsp">
        <input type="hidden" name="page" value="main">
    </form>
</div>

    ${registerMessage}

    <a href="${pageContext.request.contextPath}/controller?page=main&send=redirect">Go back</a>
</body>
</html>
