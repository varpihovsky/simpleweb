<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="https://github.com/varpihovsky/simpleweb" prefix="simpleweb" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Profile</title>
    <link rel="stylesheet" href="css/basic.css">
    <link rel="stylesheet" href="css/profile.css">
</head>
<body>
<div class="page-container">
    <div class="navbar">
        <div class="logo-text"><a href="${pageContext.request.contextPath}/controller?page=main&send=redirect">echat</a>
        </div>
        <nav>
            <simpleweb:navbar currentUser="${currentUser}"/>
        </nav>
    </div>
    <!-- Profile container -->
    <div class="container">
        <div class="profile-container">
            <div class="profile-avatar">
                <simpleweb:avatar user="${user}"/>
            </div>
            <div class="username">
                ${username}
                ${settings}
            </div>
        </div>
        <h3>Rooms</h3>
        <div class="room-grid">
            ${roomlist}
        </div>
    </div>
</div>
</body>
</html>
