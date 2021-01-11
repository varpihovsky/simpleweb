<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="https://github.com/varpihovsky/simpleweb" prefix="simpleweb" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Room create</title>
    <link rel="stylesheet" href="css/basic.css">
    <link rel="stylesheet" href="css/form.css">
    <link rel="stylesheet" href="css/login.css">
</head>
<body>
<div class="form">
    <form action="${pageContext.request.contextPath}/controller" method="post" enctype="multipart/form-data">
        <a href="${pageContext.request.contextPath}/controller?page=rooms&send=redirect" class="back">Back</a>
        <h1>Room create</h1>
        <div class="input-form">
            <input type="text" placeholder="Name" name="roomName" value="">
        </div>
        <div class="input-form">
            <input type="password" placeholder="Password (leave empty if you wish)" name="roomPassword" value="">
        </div>
        <div class="input-form" style="height: 50px;">
            <input type="text" placeholder="Description" name="roomDescription" value="">
        </div>
        <div class="input-form">
            <input type="file" name="roomLogo" value="">
        </div>
        <div class="checkbox">
            <input type="checkbox" id="check" name="isPrivate" value="yes">
            <label for="check" class="checkbox-text">Private</label>
        </div>

        <div class="input-form">
            <input type="submit" value="create">
        </div>
        <input type="hidden" name="send" value="roomCreate">
        <input type="hidden" name="page" value="roomCreate">
    </form>
</div>
</body>
</html>
