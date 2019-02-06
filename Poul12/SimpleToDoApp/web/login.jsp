<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <style>

        body
        {
            background-color: #b3daff;
        }

        h1
        {
            color: #007599;
            text-align: center;
        }

        p
        {
            font-family: verdana;
            font-size: 16px;

        }

        div
        {
            font-family: verdana;
            font-size: 16px;
            text-align: center;
        }

        form
        {
            background-color: #e6f9ff;
            border-style: outset;
            text-align: center;
            margin: 25px 500px 205px 500px;
        }

    </style>
</head>
<body>

<h1>Logowanie</h1>

<form action="login" method="post">
    <br>
    <div>Nazwa użytkownika:</div>
    <input type="text" placeholder="Nazwa użytkownika" name="username">
    <br><br>
    <div>Hasło:</div>
    <input type="password" placeholder="Hasło" name="password">
    <br><br>
    <input type="submit" value="Zaloguj">
    <br><br>

</form>

</body>
</html>
