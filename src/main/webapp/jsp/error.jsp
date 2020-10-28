<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
<title>Error page</title>
</head>
<body>
Request from ${pageContext.errorData.requestURI} is failed
<br>
Status code: ${pageContext.errorData.statusCode}
<br>
Exception: ${pageContext.errorData.throwable}
<br>
Stacktrace: ${stacktrace}
</body>
</html>