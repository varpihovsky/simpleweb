<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Form</title>
    <link rel="stylesheet" href="main.css">



</head>
<body>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>



<div class="form">
    <form action="/controller">
        <h1>Вход</h1>
        <div class="input-form">
            <input type="text" placeholder="Логин" name="username" value="">
        </div>
        <div class="input-form">
            <input type="password" placeholder="Пароль">
        </div>
        <div class="input-form">
            <input type="submit" value="Войти">
        </div>
        <a href="#" class="forget">Забыли пароль?</a>
        <input type="hidden" name="send" value="login">
        <input type="hidden" name="page" value="login.jsp">
        <input type="hidden" name="page" value="main">
    </form>
</div>

</body>
</html>