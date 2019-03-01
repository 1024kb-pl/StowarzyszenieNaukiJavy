<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>

<jsp:include page="/WEB-INF/fragments/menu.jspf"/>

<form class="register" action="login" method="post">
    <h1>Logowanie</h1>
    Nazwa użytkownika:<br>
    <input type="text" placeholder="Nazwa użytkownika" name="username">
    <br><br>
    Hasło:<br>
    <input type="password" placeholder="Hasło" name="password">
    <br><br>
    <input type="submit" value="Zaloguj">
    <br><br>
</form>

</body>
</html>
