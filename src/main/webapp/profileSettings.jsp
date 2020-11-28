<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Settings</title>
    <link rel="stylesheet" href="css/basic.css">
    <link rel="stylesheet" href="css/form.css">
    <link rel="stylesheet" href="css/settings.css">
</head>
<body>

<div class="page-container">
    <div class="navbar">
        <div class="logo-text"><a href="/controller?page=main&send=redirect">echat</a></div>
        <nav>
            <a href="#">Users</a>
            <a href="/controller?page=login&send=redirect">Register</a>
            <a href="#">News</a>
            <a href="#">Rooms</a>
            <a href="/controller?page=login&send=redirect">Login</a>
        </nav>
    </div>
    <div class="container">
        <h1>Profile settings</h1>
        <form class="form" action="/controller">
            <div class="input-form">
                <label for="username">Username:</label>
                <input type="text" id="username" name="newusername" placeholder="${username}" value="">
            </div>
            <div class="input-form">
                <label for="email">Email: </label>
                <input type="email" id="email" name="newemail" placeholder="${email}" value="">
            </div>
            <div class="input-form">
                <label for="newpassword">New password: </label>
                <input type="password" id="newpassword" name="newpassword" placeholder="New password" value="">
            </div>
            <div class="input-form">
                <label for="currentpassword">Current password: </label>
                <input type="password" id="currentpassword" name="oldpassword" placeholder="Old password" value="">
            </div>
            <div class="input-form">
                <input type="submit" value="Confirm">
            </div>
            <input type="hidden" name="page" value="profileSettings">
            <input type="hidden" name="send" value="change">
        </form>
    </div>
</div>
</body>
</html>