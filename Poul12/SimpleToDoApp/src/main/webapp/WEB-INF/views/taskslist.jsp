<%@ page import="java.time.LocalDate" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<head>
    <title>Task list</title>
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

<div class="row">

    <div class="col-md-4 col-md-offset-3">

        <div class="form-group">
            <form class="form-inline" action="tasks" method="post">
                <label for="filters">Filters </label>
                <select class="form-control" id="filters" name="filter">
                    <option></option>
                    <option value="true">Checked</option>
                    <option value="false">Unchecked</option>
                    <option value="date">Date</option>
                </select>
                <input type="date" name="dateFilter" value="<c:out value="${LocalDate.now()}"/>">
                <button type="submit" class="btn btn-primary">Filter</button>
            </form>
        </div>

    </div>

    <div class="col-md-4">

        <div class="form-group">
            <form class="form-inline" action="tasks" method="post">
                <label for="sort">Sort </label>
                <select class="form-control" id="sort" name="sort">
                    <option></option>
                    <option value="title">Title</option>
                    <option value="date">Date</option>
                    <option value="status">Checked</option>
                </select>
                <button type="submit" class="btn btn-primary">Sort</button>
            </form>
        </div>

    </div>

</div>

<div class="container">

    <table class="table table-hover">
        <thead class="thead-dark">
        <tr>
            <th scope="col">#</th>
            <th scope="col">Title</th>
            <th scope="col">Date</th>
            <th scope="col">Description</th>
            <th scope="col">Done</th>
            <th scope="col">Operation</th>
        </tr>
        </thead>

        <c:if test="${not empty requestScope.tasksList}">
            <% int row = 1;%>
            <c:forEach var="task" items="${requestScope.tasksList}">

                <tbody>
                <tr>
                    <th scope="row"><%=row++%>
                    </th>
                    <td>${task.title}</td>
                    <td>${task.date}</td>
                    <td>${task.description}</td>
                    <td>
                        <c:choose>
                            <c:when test="${task.taskDone == true}">
                                <span class="glyphicon glyphicon-ok"></span>
                            </c:when>
                            <c:otherwise>
                                <span class="glyphicon glyphicon-remove"></span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a href="editTask?id=${task.taskId}&title=${task.title}&date=${task.date}&description=${task.description}&done=${task.taskDone}">
                            <button type="button" class="btn btn-primary">Edit</button>
                        </a>
                        <a href="deleteTask?id=${task.taskId}">
                            <button type="button" class="btn btn-primary">Delete</button>
                        </a>
                    </td>
                </tr>
                </tbody>

            </c:forEach>
        </c:if>

    </table>

</div>

<footer class="footer">
    <div class="container">
        <p class="navbar-text">Simple ToDo App - developed by Poul12 2019</p>
    </div>
</footer>


</body>
</html>
