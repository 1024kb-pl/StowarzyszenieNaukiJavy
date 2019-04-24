<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Main Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-inverse">

    <div class="container">

        <div class="navbar-header">

            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="glyphicon glyphicon-list"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}">SimpleTodoApp</a>

        </div>

        <div class="collapse navbar-collapse" id="myNavbar">

            <ul class="nav navbar-nav">
                <li class="active"><a href="${pageContext.request.contextPath}">Home</a></li>
                <li><a href="addTask">Create task</a></li>
                <li><a href="tasks">Display task</a></li>
                <li><a href="#">About</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:choose>
                    <c:when test="${empty sessionScope.username}">
                        <li><a href="register"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                        <li><a href="login"><span class="glyphicon glyphicon-log-in"></span> Sign In</a></li>
                    </c:when>
                    <c:otherwise>
                        <li>
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span
                                    class="glyphicon glyphicon-user"></span><c:out
                                    value="${sessionScope.username}"/></a>
                            <ul class="dropdown-menu">
                                <li><a href="editUser">Edit Account</a></li>
                                <li><a href="deleteUser">Delete Account</a></li>
                            </ul>
                        </li>
                        <li><a href="logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>

        </div>

    </div>

</nav>

<div class="container">
    <h3>Simple ToDo App</h3>
    <p>You can store tasks on this website.
    <p>Create new task, display it, and manage. Check for done, delete old task. </p>
</div>

<footer class="footer">
    <div class="container">
        <p class="navbar-text">Simple ToDo App - developed by Poul12 2019</p>
    </div>
</footer>

</body>
</html>