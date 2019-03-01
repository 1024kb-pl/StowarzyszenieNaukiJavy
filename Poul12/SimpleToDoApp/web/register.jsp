<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>

<jsp:include page="/WEB-INF/fragments/menu.jspf"/>

<form class="register" action="register" method="post">
    <h1>Rejestracja</h1>
    <div>Nazwa użytkownika:</div>
    <input type="text" placeholder="Nazwa użytkownika" name="username">
    <br><br>
    <div>Hasło:</div>
    <input type="password" placeholder="Hasło" name="password">
    <br><br>
    <div>Powtórz hasło:</div>
    <input type="password" placeholder="Powtórzone hasło" name="repeated-pass">
    <br><br>
    <div>E-mail:</div>
    <input type="email" placeholder="E-mail" name="email">
    <br><br>
    <input type="submit" value="Zarejestruj">
    <br><br>
</form>


</body>
</html>
