<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Main</title>
    <link rel="stylesheet" href="css/basic.css">
</head>
<body>
<div class="page-container">
    <div class="navbar">
        <div class="logo-text"><a href="/controller?page=main&send=redirect">echat</a></div>
        <nav>
            ${navbar}
        </nav>
    </div>
    <div class="container">
        <div class="grid">
            <div class="about">
                <h1>Echat</h1>
                <p>Echat - free opensource platform where you can communicate with other users freely.
                    Create public rooms where user can find your room and join to it freely, private rooms where user
                    can join to it only with invite code and protected rooms where user can see your room in room list
                    but can join only with a password. You can add a rooles to your room or have full anarchy in it.</p>
            </div>
            <div class="about">
                <h1>Open and free</h1>
                <p>As was told before, echat is opensource project, so if you have skills you can help project to
                    develop. Just do pull request and contributors will think about it. No analytics or information
                    about users collected.
                </p>
            </div>
        </div>
    </div>
</div>
</body>
</html>