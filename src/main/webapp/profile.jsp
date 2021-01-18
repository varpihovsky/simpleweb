<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="https://github.com/varpihovsky/simpleweb" prefix="simpleweb" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
            <simpleweb:navbar currentUser="${currentUser}" contextPath="${pageContext.request.contextPath}"/>
        </nav>
    </div>
    <div class="container">
        <div class="profile-container">
            <div class="profile-avatar">
                <c:set var="avatar">
                    <simpleweb:avatar user="${user}"/>
                </c:set>
                <img src="${avatar}" alt="avatar"/>
            </div>
            <div class="username">
                ${user.getUsername()}
                <c:if test="${showSettings}">
                    <div class=\"profile-settings\">
                        <a href="${pageContext.request.contextPath}/controller?page=profileSettings&send=redirect">
                            <img src="${pageContext.request.contextPath}/img/interface/settings.svg" alt="settings"/>
                        </a>
                    </div>
                </c:if>
            </div>
        </div>
        <h3>Rooms</h3>
        <div class="room-grid">
            <c:forEach var="room" items="${roomList}">
                <c:set var="path">
                    <simpleweb:getRoomLogo room="${room}"/>
                </c:set>
                <a href="#">
                    <div class="room">
                        <h4>${room.name}</h4>
                        <img src="${path}" alt="room logo"/>
                        <div>${room.description}</div>
                    </div>
                </a>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>
