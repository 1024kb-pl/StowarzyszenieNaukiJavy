
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Main Page</title>
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
      text-align: center;
    }

    div
    {
      font-family: verdana;
      font-size: 16px;
      text-align: center;
    }

  </style>
</head>
<body>

<h1>Menu</h1>

<br>
<%
  if(request.getSession(false).getAttribute("username") == null)
  {
%>
<p><a href="register.jsp"><button type="button">Zarejestruj się</button></a></p>
<p><a href="login.jsp"><button type="button">Zaloguj się</button></a></p>
<%
  }else
  {
%>
<p><a href="logout"><button type="button">Wyloguj się</button></a></p>
<%
  }
%>
<p><a href="tasks.jsp"><button type="button">Zadania</button></a></p>

</body>
</html>
