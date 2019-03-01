
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Message</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>

<jsp:include page="/WEB-INF/fragments/menu.jspf"/>

<h3>${requestScope.message}</h3>

</body>
</html>