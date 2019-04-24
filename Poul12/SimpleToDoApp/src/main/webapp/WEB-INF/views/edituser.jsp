<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit user</title>
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

<c:if test="${not empty sessionScope.username}">

    <div class="container">

        <div class="row">

            <div class="col-sm-6 col-md-4 col-md-offset-4">

                <form action="editUser" method="post">
                    <h2>Change user data</h2>
                    <input type="hidden" name="userId" value="${requestScope.userId}">
                    <div class="form-group">
                        <label for="InputUsername">Username</label>
                        <input type="text" class="form-control" id="InputUsername" placeholder="Enter username"
                               name="username" value="${requestScope.username}" required>
                    </div>
                    <div class="form-group">
                        <label for="InputEmail">Email address</label>
                        <input type="email" class="form-control" id="InputEmail" placeholder="Enter email" name="email"
                               value="${requestScope.email}" required>
                    </div>
                    <div class="form-group">
                        <label for="InputPassword">Password</label>
                        <input type="password" class="form-control" id="InputPassword" placeholder="Password"
                               name="password" required>
                    </div>
                    <div class="form-group">
                        <label for="InputRepeatedPassword">Repeat password</label>
                        <input type="password" class="form-control" id="InputRepeatedPassword"
                               placeholder="Repeat password" name="repeated-pass" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Save</button>
                </form>

            </div>

        </div>
    </div>

</c:if>

<footer class="footer">
    <div class="container">
        <p class="navbar-text">Simple ToDo App - developed by Poul12 2019</p>
    </div>
</footer>

</body>
</html>