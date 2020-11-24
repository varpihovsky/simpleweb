<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <div class="logo-text">echat</div>
        <nav>
            <a href="#">Users</a>
            <a href="/controller?page=login&send=redirect">Register</a>
            <a href="#">News</a>
            <a href="#">Rooms</a>
            <a href="/controller?page=login&send=redirect">Login</a>
        </nav>
    </div>
    <!-- Profile container -->
    <div class="container">
        <div class="profile-container">
            <div class="profile-avatar">
                <img src="img/avatar.jpg" alt="avatar"/>
            </div>
            <div class="username">
                ${username}
                <div class="profile-settings">
                    <a href="/controller?page=profileSettings&send=redirect">
                        <img src="img/settings.svg" alt="settings"/>
                    </a>
                </div>
            </div>
        </div>
        <h3>Rooms</h3>
        <div class="room-grid">
            <a href="#">
                <div class="room">
                    <h4>Room 1</h4>
                    <img src="img/roomlogo.jpg" alt="room logo"/>
                    <div>
                        Sometext. Sometext. Sometext. Sometext. Sometext. Sometext. Sometext. Sometext. Sometext.
                        Sometext. Sometext. Sometext. Sometext.
                    </div>
                </div>
            </a>
            <a href="#">
                <div class="room">
                    <h4>Room 2</h4>
                    <img src="img/roomlogo1.webp" alt="room logo"/>
                    <div>
                        Sometext. Sometext. Sometext. Sometext. Sometext. Sometext. Sometext. Sometext. Sometext.
                        Sometext. Sometext. Sometext. Sometext.
                    </div>
                </div>
            </a>
            <a href="#">
                <div class="room">
                    <h4>Room 3</h4>
                    <img src="img/roomlogo2.jpg" alt="room logo"/>
                    <div>
                        Sometext. Sometext. Sometext. Sometext. Sometext. Sometext. Sometext. Sometext. Sometext.
                        Sometext. Sometext. Sometext. Sometext.
                    </div>
                </div>
            </a>
            <a href="#">
                <div class="room">
                    <h4>Room 4</h4>
                    <img src="img/roomlogo4.jpg" alt="room logo"/>
                    <div>
                        Sometext. Sometext. Sometext. Sometext. Sometext. Sometext. Sometext. Sometext. Sometext.
                        Sometext. Sometext. Sometext. Sometext.
                    </div>
                </div>
            </a>
        </div>
    </div>
</div>
</body>
</html>
