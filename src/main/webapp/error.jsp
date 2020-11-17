<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>Error</title>
</head>
<body>
Request from ${pageContext.errorData.requestURI} is failed
<br/>
Error №${pageContext.errorData.statusCode}
<br/>
Exception: ${pageContext.errorData.throwable}
<br/>
<a href="/controller?page=main&send=redirect">Go back</a>
</body>
</html>
