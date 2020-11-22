<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Register</title>
    <link rel="stylesheet" href="css/register.css">
    <link rel="stylesheet" href="css/form.css">
</head>
<body>
<div class="form">
    <form action="${pageContext.request.contextPath}/controller" method="post">
        <a href="${pageContext.request.contextPath}/controller?page=main&send=redirect" class="back">Back</a>
        <h1>Регистрация</h1>
        <div class="input-form">
            <input type="text" placeholder="Логин" name="username" value="">
        </div>
        <div class="input-form">
            <input type="password" placeholder="Пароль" name="password" value="">
        </div>

        <div class="input-form">
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
</body>
</html>
