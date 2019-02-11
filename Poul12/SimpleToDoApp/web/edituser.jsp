<%@ page import="pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity.User" %>
<%@ page import="pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.service.UserServiceImpl"%>
<%@ page import="java.util.Optional" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit user</title>
    <style>
        body
        {
            background-color: #b3daff;
        }

        h1
        {
            color: white;
            text-align: center;
        }

        h3
        {
            color: #007599;
            text-align: center;
            background-color: #e6f9ff;
            border-style: outset;
            margin: 50px 500px 15px 500px;
        }

        div
        {
            font-family: verdana;
            font-size: 14px;
            text-align: center;
            margin-left: 25px;
        }

        p
        {
            font-family: verdana;
            font-size: 14px;
            text-align: center;
        }

        form
        {
            text-align: center;
            border-style: solid;
            border-width: 3px;
            border-color: #80c1ff;
            background-color: white;
            margin: 10px 500px 50px 500px;

        }

    </style>
</head>
<body>

<h1>Edytuj użytkownika</h1>

<p><a href="index.jsp"><button type="button">Menu</button></a></p>

<%
    UserServiceImpl userService = UserServiceImpl.getInstance();
    Optional<User> user = userService.getUserByUsername((String)request.getSession(false).getAttribute("username"));
            if(user.isPresent())
            {%>

<br>
<form action="editUser" method="post">
    <br>
    <div>Nazwa użytkownika:</div>
    <input type="text" name="username" placeholder="Nazwa użytkownika" value="<%=user.get().getUsername()%>">
    <br><br>
    <div>Nowe hasło:</div>
    <input type="password" name="password" value="<%=user.get().getPassword()%>">
    <br><br>
    <div>Powtórz hasło:</div>
    <input type="password" name="repeated-pass" value="<%=user.get().getPassword()%>" >
    <br><br>
    <div>Adres e-mail</div>
    <input type="email" name="email" value="<%=user.get().getEmail()%>">
    <br><br>
    <input type="submit" value="Zaktualizuj użytkownika">
    <br><br>
</form>

<%}else{%>

<h3>Nie znaleziono użytkownika</h3>

<%}%>

</body>
</html>