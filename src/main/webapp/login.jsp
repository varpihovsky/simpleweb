<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="css/login.css">
    <link rel="stylesheet" href="css/form.css">

</head>
<body>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
        crossorigin="anonymous"></script>


<div class="form">
    <form action="${pageContext.request.contextPath}/controller" method="post">
        <a href="${pageContext.request.contextPath}/controller?page=main&send=redirect" class="back">Back</a>
        <h1>Вход</h1>
        <div class="input-form">
            <input type="text" placeholder="Логин" name="username" value="">
        </div>
        <div class="input-form">
            <input type="password" placeholder="Пароль" name="password" value="">
        </div>

        <div class="checkbox">
            <input type="checkbox" id="check" name="cookie" value="true">
            <label for="check" class="checkbox-text">Запомнить меня</label>
        </div>

        <div class="input-form">
            <input type="submit" value="Войти">
        </div>
        <a href="#" class="forget">Забыли пароль?</a>
        <input type="hidden" name="send" value="login">
        <input type="hidden" name="page" value="login">
    </form>
</div>
</body>
</html>