<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Session id: ${pageContext.session.id}
<br/>
Max inactive interval: ${pageContext.session.maxInactiveInterval}
<br/>
Username: ${username}
<br/>
Password: ${password}
</body>
</html>
