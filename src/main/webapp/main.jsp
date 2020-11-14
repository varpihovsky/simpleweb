<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Welcome!</title>
</head>
<body>
    <div>
        <h4>Welcome!!!</h4>
        <p>This is the first project.</p>
        <div><%= request.getAttribute("date")%></div>
    </div>
</body>
</html>
