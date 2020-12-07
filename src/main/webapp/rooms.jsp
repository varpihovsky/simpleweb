<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Users</title>
    <link rel="stylesheet" href="css/basic.css">
    <link rel="stylesheet" href="css/form.css">
    <link rel="stylesheet" href="css/users.css">
</head>
<body>
<div class="page-container">
    <div class="navbar">
        <div class="logo-text"><a href="${pageContext.request.contextPath}/controller?page=main&send=redirect">echat</a>
        </div>
        <nav>
            ${navbar}
        </nav>
    </div>
    <div class="container">
        <div class="form">
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <div class="find-form">
                    <div class="input-form">
                        <input type="text" name="room" value="">
                    </div>
                    <div class="input-form" id="find-button">
                        <input type="submit" value="Find">
                    </div>
                    <input type="hidden" name="page" value="rooms">
                    <input type="hidden" name="send" value="redirect">
                </div>
            </form>
        </div>
        <div class="user-grid">
            ${render}
        </div>
    </div>
</div>
</body>
</html>
