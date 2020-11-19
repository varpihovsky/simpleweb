<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="css/register.css">
</head>
<body>
<div class="form">
    <form action="${pageContext.request.contextPath}/controller" method="post">
        <h1>Регистрация</h1>
        <div class="input-form">
            <p style="color:#FFFFFF"><big>Придумайте логин</big> <br>
                <input type="text" placeholder="Логин" name="username" value="">
        </div>
        <div class="input-form">
            <p style="color:#FFFFFF"><big>Придумайте пароль</big> <br>
                <input type="password" placeholder="Пароль" name="password" value="">
        </div>

        <div class="input-form">
            <p style="color:#FFFFFF"><big>Введите почту</big> <br>
                <input type="text" placeholder="Почта" name="email" value="">
        </div>
        <div class="input-form">
            <input type="submit" value="Регистрация">
        </div>

        <input type="hidden" name="send" value="register">
        <input type="hidden" name="page" value="register">
    </form>
</div>

    ${registerMessage}

    <a href="${pageContext.request.contextPath}/controller?page=main&send=redirect">Go back</a>
</body>
</html>
